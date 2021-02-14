package com.ebi.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebi.springboot.model.Person;
/**
 * Repository for a person
 * @author Yogesh Patil
 *
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
