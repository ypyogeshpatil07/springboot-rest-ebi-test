package com.ebi.springboot.service;

import java.util.List;

import com.ebi.springboot.model.Person;
/**
 * Interface for crud methods for a person
 * @author Yogesh Patil
 *
 */
public interface PersonService {
	Person storePerson(Person person);

	Person updatePerson(Person person);

	List<Person> getAllPerson();

	Person getPersonById(long personId);

	void deletePerson(long id);
}
