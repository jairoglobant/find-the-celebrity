package com.findthecelebrity.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.findthecelebrity.database.PersonEntity;
import com.findthecelebrity.database.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
public class CelebrityTest {
	
	@Mock
	private PersonRepository personRepository;
	
	private Celebrity celebrity;
	private PersonEntity celebrityExpected;

	@Before
	public void init() {
		celebrity = new Celebrity(personRepository);
	}

	@Test
	public void onePersonWithoutKnownPeopleIsExpected() throws Exception {
		prepareOneCelebrity();
		PersonEntity celebrityFound = this.celebrity.findTheCelebrity();
		assertEquals(celebrityExpected.getKnownPeople(), celebrityFound.getKnownPeople());
	}

	@Test(expected=BusinessException.class)
	public void anyPersonWithoutKnownPeopleIsExpected() throws Exception {
		prepareAnyPersonWithoutKnownPeopleIsExpected();
		PersonEntity celebrityFound = this.celebrity.findTheCelebrity();
	}
	
	@Test
	public void oneCelebrityIsExpected() throws Exception {
		prepareOneCelebrity();
		PersonEntity celebrityFound = this.celebrity.findTheCelebrity();
		assertEquals(celebrityExpected, celebrityFound);
	}
	
	@Test(expected=BusinessException.class)
	public void noPeople() throws Exception {
		prepareOnePeople();
		PersonEntity celebrityFound = this.celebrity.findTheCelebrity();
	}
	
	@Test(expected=BusinessException.class)
	public void morThanOneCelebrity() throws Exception {
		prepareTwoCelebrities();
		PersonEntity celebrityFound = this.celebrity.findTheCelebrity();
	}

	private void prepareAnyPersonWithoutKnownPeopleIsExpected() {
		Set<PersonEntity> peopleSet = new HashSet<PersonEntity>();
		PersonEntity person = new PersonEntity("person",null);
		PersonEntity person2 = new PersonEntity("person",null);
		person.addKnownPerson(person2);
		person2.addKnownPerson(person);
		peopleSet.add(person);
		peopleSet.add(person2);
		when(personRepository.findAll()).thenReturn(peopleSet);
	}
	
	private void prepareOneCelebrity() {
		Set<PersonEntity> peopleSet = new HashSet<PersonEntity>();
		PersonEntity person = new PersonEntity("person",null);
		celebrityExpected = new PersonEntity("celebrity",null);
		person.addKnownPerson(celebrityExpected);
		peopleSet.add(celebrityExpected);
		peopleSet.add(person);
		when(personRepository.findAll()).thenReturn(peopleSet);
	}
	
	private void prepareOnePeople() {
		Set<PersonEntity> peopleSet = new HashSet<PersonEntity>();
		when(personRepository.findAll()).thenReturn(peopleSet);
	}
	
	private void prepareTwoCelebrities() {
		Set<PersonEntity> peopleSet = new HashSet<PersonEntity>();
		PersonEntity person = new PersonEntity("person",null);
		PersonEntity  celebrity1 = new PersonEntity("celebrity",null);
		PersonEntity  celebrity2 = new PersonEntity("celebrity",null);
		person.addKnownPerson(celebrity1);
		person.addKnownPerson(celebrity2);
		peopleSet.add(celebrity1);
		peopleSet.add(celebrity2);
		peopleSet.add(person);
		when(personRepository.findAll()).thenReturn(peopleSet);
	}
	
	
}
