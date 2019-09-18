package com.findthecelebrity.domain;

import java.util.Set;

import com.findthecelebrity.dataproviders.database.Person;

public interface DataProvider {
	public Set<Person> retrivePeople();
}
