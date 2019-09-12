package com.findthecelebrity.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.findthecelebrity.database.PersonEntity;
import com.findthecelebrity.database.PersonRepository;

public class Celebrity {
	
	private PersonRepository personRepository;
	
	public Celebrity(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	
	public PersonEntity findTheCelebrity() {
			Set<PersonEntity> people = StreamSupport.stream(personRepository.findAll().spliterator(),false).collect(Collectors.toSet());
			
			List<PersonEntity> peopleWithotKnown = people.stream().filter(person -> hasntKnownPeople(person)).collect(Collectors.toList());
			List<PersonEntity> celebrity = peopleWithotKnown.stream().filter(person -> isKnownForAll(person,people)).collect(Collectors.toList());
			if(celebrity.size() == 0 ) {
				throw new BusinessException("No one Celebrity found");
			}
			
			return celebrity.get(0);
		
	}

	private boolean isKnownForAll(PersonEntity celebrity, Set<PersonEntity> people) {
		for (PersonEntity person : people) {
			if(person!=celebrity &&  !person.getKnownPeople().contains(celebrity)) {
				return false;
			}
		}
		return true;
	}


	private boolean hasntKnownPeople(PersonEntity person) {
		return  person.getKnownPeople()==null || person.getKnownPeople().size() == 0;
	}

}
