package com.findthecelebrity.database;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class PersonEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<PersonEntity> knownPeople;
	
	public PersonEntity() {}
	
	public PersonEntity(String name,Set<PersonEntity> knownPeople) {
		this.name = name;
		this.knownPeople = knownPeople;
	}

	public void addKnownPerson(PersonEntity knownPerson) {
		if(knownPeople == null) {
			knownPeople = new HashSet<>();
		}
		knownPeople.add(knownPerson);
	}
	
	public String getName() {
		return name;
	}
	
	public Set<PersonEntity> getKnownPeople(){
		if(knownPeople == null) {
			knownPeople = new HashSet<>();
		}
		return knownPeople;
	}
	
	
}
