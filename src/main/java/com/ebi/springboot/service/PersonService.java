package com.ebi.springboot.service;

import java.util.List;

import com.ebi.springboot.exception.InvalidRequestException;
import com.ebi.springboot.model.Person;
/**
 * Interface for crud methods for a person
 * @author Yogesh Patil
 *
 */
public interface PersonService {
	Person storePerson(Person person) throws InvalidRequestException;

	Person updatePerson(Person person) throws InvalidRequestException;

	List<Person> getAllPerson();

	Person getPersonById(long personId);

	void deletePerson(long id);
}
