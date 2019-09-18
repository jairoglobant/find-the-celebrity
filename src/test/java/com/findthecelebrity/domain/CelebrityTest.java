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

import com.findthecelebrity.dataproviders.database.Person;

@RunWith(MockitoJUnitRunner.class)
public class CelebrityTest {

	@Mock
	private DataProvider dataProvider;

	private Celebrity celebrity;
	private Person celebrityExpected;
	private Set<Person> peopleSet;

	@Before
	public void init() {
		celebrity = new Celebrity(dataProvider);
		peopleSet = new HashSet<Person>();
		when(dataProvider.retrivePeople()).thenReturn(peopleSet);
	}

	@Test(expected = BusinessException.class)
	public void anyPersonWithoutKnownPeopleIsExpected() {
		prepareAnyPersonWithoutKnownPeople();
		this.celebrity.findTheCelebrity();
	}

	@Test
	public void oneCelebrityIsExpected() {
		prepareOneCelebrity();
		Person celebrityFound = this.celebrity.findTheCelebrity();
		assertEquals(celebrityExpected, celebrityFound);
	}

	@Test(expected = BusinessException.class)
	public void noPeople() {
		this.celebrity.findTheCelebrity();
	}

	@Test(expected = BusinessException.class)
	public void morThanOneCelebrity() {
		prepareTwoCelebrities();
		this.celebrity.findTheCelebrity();
	}

	private void prepareAnyPersonWithoutKnownPeople() {
		Person person = new Person("person", null);
		Person person2 = new Person("person", null);
		person.addKnownPerson(person2);
		person2.addKnownPerson(person);
		peopleSet.add(person);
		peopleSet.add(person2);
	}

	private void prepareOneCelebrity() {
		Person person = new Person("person", null);
		celebrityExpected = new Person("celebrity", null);
		person.addKnownPerson(celebrityExpected);
		peopleSet.add(celebrityExpected);
		peopleSet.add(person);
	}

	private void prepareTwoCelebrities() {
		Person person = new Person("person", null);
		Person celebrity1 = new Person("celebrity", null);
		Person celebrity2 = new Person("celebrity", null);
		person.addKnownPerson(celebrity1);
		person.addKnownPerson(celebrity2);
		peopleSet.add(celebrity1);
		peopleSet.add(celebrity2);
		peopleSet.add(person);
	}

}
