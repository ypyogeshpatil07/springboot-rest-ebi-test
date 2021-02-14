package com.ebi.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebi.springboot.exception.ResourceNotFoundException;
import com.ebi.springboot.model.Person;
import com.ebi.springboot.repository.PersonRepository;
/**
 * Service Implementation class for Person service
 * @author Yogesh Patil
 *
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService{

	
	@Autowired
	private PersonRepository productRepository;
	
	
	@Override
	public Person storePerson(Person person) {
		return productRepository.save(person);
	}

	@Override
	public Person updatePerson(Person person) {
		Optional<Person> personDb = this.productRepository.findById(person.getId());
		
		if(personDb.isPresent()) {
			Person personUpdate = personDb.get();
			personUpdate.setId(person.getId());
			personUpdate.setFirst_name(person.getFirst_name());
			personUpdate.setLast_name(person.getLast_name());
			personUpdate.setAge(person.getAge());
			personUpdate.setFavourite_colour(person.getFavourite_colour());
			productRepository.save(personUpdate);
			return personUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + person.getId());
		}		
	}

	@Override
	public List<Person> getAllPerson() {
		return this.productRepository.findAll();
	}

	@Override
	public Person getPersonById(long personId) {
		
		Optional<Person> personDb = this.productRepository.findById(personId);
		
		if(personDb.isPresent()) {
			return personDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + personId);
		}
	}

	@Override
	public void deletePerson(long personId) {
		Optional<Person> personDb = this.productRepository.findById(personId);
		
		if(personDb.isPresent()) {
			this.productRepository.delete(personDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + personId);
		}
		
	}

}
