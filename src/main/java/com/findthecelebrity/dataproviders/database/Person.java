package com.findthecelebrity.dataproviders.database;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Person> knownPeople;
	
	public Person() {}
	
	public Person(String name,Set<Person> knownPeople) {
		this.name = name;
		this.knownPeople = knownPeople;
	}

	public void addKnownPerson(Person knownPerson) {
		if(knownPeople == null) {
			knownPeople = new HashSet<>();
		}
		knownPeople.add(knownPerson);
	}
	
	public String getName() {
		return name;
	}
	
	public Set<Person> getKnownPeople(){
		if(knownPeople == null) {
			knownPeople = new HashSet<>();
		}
		return knownPeople;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", knownPeople=" + knownPeople.size() + "]";
	}
	
	
}
