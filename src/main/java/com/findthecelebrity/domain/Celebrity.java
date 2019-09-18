package com.findthecelebrity.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.findthecelebrity.dataproviders.database.Person;

public class Celebrity {
	
	private static final Logger log = LoggerFactory.getLogger(Celebrity.class);
	
	
	private DataProvider dataProvider;
	
	public Celebrity(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}
	
	public Person findTheCelebrity() {
			Set<Person> people = dataProvider.retrivePeople();
			people.forEach(this::LogPerson);
			List<Person> celebrity = people.stream().filter(person -> person.getKnownPeople().isEmpty() && isKnownForAll(person, people) ).collect(Collectors.toList());
			if(celebrity.isEmpty()) {
				throw new BusinessException("No one Celebrity found");
			}
			return celebrity.get(0);
	}
	
	private void LogPerson(Person person) {
		log.info(person.toString());
	}
	
	private boolean isKnownForAll(Person celebrity, Set<Person> people) {
		for (Person person : people) {
			if(person!=celebrity &&  !person.getKnownPeople().contains(celebrity)) {
				return false;
			}
		}
		return true;
	}

}
