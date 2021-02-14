package com.ebi.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebi.springboot.exception.InvalidRequestException;
import com.ebi.springboot.model.Person;
import com.ebi.springboot.model.PersonList;
import com.ebi.springboot.service.PersonService;



/**Controller for person REST Crud endpoints.
 * @author Yogesh Patil
 *
 */
@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	
	/**
	 * REST endpoint for testing the application
	 * @param 
	 * @return
	 */
	@GetMapping("/")
	public String home(){
		return "Welcome To Home";
	}
	
	/**
	 * REST endpoint for get all person
	 * @param
	 * @return
	 */
	@PreAuthorize("hasAuthority('READ_PRIVILEGES')")
	@GetMapping("/person-all")
	public PersonList getAllPerson(){
		List<Person> personList = personService.getAllPerson();

        if (Objects.nonNull(personList)) {
            return new PersonList(personList);
        }

        return new PersonList(new ArrayList<>());
	}
	/**
	 * REST endpoint for getting person by id
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('READ_PRIVILEGES')")
	@GetMapping("/person-by-id/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable long id){
		return ResponseEntity.ok().body(personService.getPersonById(id));
	}
	
	/**
	 * REST endpoint for storing person
	 * @param person
	 * @return
	 * @throws InvalidRequestException 
	 */
	@PreAuthorize("hasAuthority('WRITE_PRIVILEGES')")
	@PostMapping("/store-person")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) throws InvalidRequestException{
		return ResponseEntity.ok().body(this.personService.storePerson(person));
	}
	
	/**
	 * REST endpoint for updating person for a particular id
	 * @param id
	 * @param person
	 * @return
	 * @throws InvalidRequestException 
	 */
	@PreAuthorize("hasAuthority('WRITE_PRIVILEGES')")
	@PutMapping("/update-person/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable long id, @RequestBody Person person) throws InvalidRequestException{
		person.setId(id);
		return ResponseEntity.ok().body(this.personService.updatePerson(person));
	}
	/**
	 * REST endpoint for deleting person
	 * @param id
	 * @return
	 */
	
	@PreAuthorize("hasAuthority('WRITE_PRIVILEGES')")
	@DeleteMapping("/delete-person/{id}")
	public HttpStatus deleteProduct(@PathVariable long id){
		this.personService.deletePerson(id);
		return HttpStatus.OK;
	}
}
