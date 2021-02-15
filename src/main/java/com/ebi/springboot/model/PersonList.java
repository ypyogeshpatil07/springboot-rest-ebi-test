package com.ebi.springboot.model;

import java.util.List;

/**
 * List class for person
 * @author Yogesh Patil
 *
 */
public class PersonList {
	
	private List<Person> person;
	
	

	public PersonList(List<Person> person) {
		super();
		this.person = person;
	}

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}
	
	

}
