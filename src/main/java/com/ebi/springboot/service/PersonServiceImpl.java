package com.ebi.springboot.service;

import static com.ebi.springboot.util.PersonConstants.RESOURCE_NOT_FOUND;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebi.springboot.exception.InvalidRequestException;
import com.ebi.springboot.exception.ResourceNotFoundException;
import com.ebi.springboot.model.Person;
import com.ebi.springboot.repository.PersonRepository;
import com.ebi.springboot.validation.ValidationUtil;


/**
 * Service Implementation class for Person service
 * @author Yogesh Patil
 *
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService{

	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private  Validator validator;
	
	
	@Override
	public Person storePerson(Person person) throws InvalidRequestException {
		//check validation
		checkValidationForRequest(person);
		return personRepository.save(person);
	}

	

	@Override
	public Person updatePerson(Person person) throws InvalidRequestException {
		//check validation
		checkValidationForRequest(person);
		Optional<Person> personDb = this.personRepository.findById(person.getId());
		
		if(personDb.isPresent()) {
			Person personUpdate = personDb.get();
			personUpdate.setId(person.getId());
			personUpdate.setFirstName(person.getFirstName());
			personUpdate.setLastName(person.getLastName());
			personUpdate.setAge(person.getAge());
			personUpdate.setFavouriteColour(person.getFavouriteColour());
			personRepository.save(personUpdate);
			return personUpdate;
		}else {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + person.getId());
		}		
	}

	@Override
	public List<Person> getAllPerson() {
		return this.personRepository.findAll();
	}

	@Override
	public Person getPersonById(long personId) {
		
		Optional<Person> personDb = this.personRepository.findById(personId);
		
		if(personDb.isPresent()) {
			return personDb.get();
		}else {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + personId);
		}
	}

	@Override
	public void deletePerson(long personId) {
		Optional<Person> personDb = this.personRepository.findById(personId);
		
		if(personDb.isPresent()) {
			this.personRepository.delete(personDb.get());
		}else {
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + personId);
		}
		
	}
	
	private void checkValidationForRequest(Person person) throws InvalidRequestException {
		// Validate DTO
        Map<String, String> errors = ValidationUtil.validateDTO(person, validator);

        // If validation errors, throw with validation constraints
        if (!errors.isEmpty()) {
            throw new InvalidRequestException(errors);
        }
	}

}
