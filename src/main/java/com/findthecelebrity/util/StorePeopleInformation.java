package com.findthecelebrity.util;

import com.findthecelebrity.dataproviders.database.Person;
import com.findthecelebrity.dataproviders.database.PersonRepository;

public class StorePeopleInformation {

	public static void storePeopleWithoutCelebrity(PersonRepository repository) {
		Person keanu = new Person("Keanu Reevs",null);
		repository.save(keanu);
		
		Person jhon = new Person("Jhon",null);
		jhon.addKnownPerson(keanu);
		repository.save(jhon);
		
		Person matt = new Person("Matt",null);
		matt.addKnownPerson(keanu);
		matt.addKnownPerson(jhon);
		repository.save(matt);
		
		keanu.addKnownPerson(jhon);
		repository.save(keanu);
	}

	public static void storePeopleWithCelebrity(PersonRepository repository) {
		Person keanu = new Person("Keanu Reevs",null);
		repository.save(keanu);
		
		Person jhon = new Person("Jhon",null);
		jhon.addKnownPerson(keanu);
		repository.save(jhon);
		
		Person matt = new Person("Matt",null);
		matt.addKnownPerson(keanu);
		matt.addKnownPerson(jhon);
		repository.save(matt);
	}

}
