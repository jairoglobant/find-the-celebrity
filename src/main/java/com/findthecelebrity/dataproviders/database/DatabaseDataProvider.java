package com.findthecelebrity.dataproviders.database;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.findthecelebrity.domain.DataProvider;

public class DatabaseDataProvider implements DataProvider{

	private PersonRepository personRepository;
	
	public DatabaseDataProvider(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public Set<Person> retrivePeople() {
		Set<Person> people = StreamSupport.stream(personRepository.findAll().spliterator(),false).collect(Collectors.toSet());
		return people;
	}

}
