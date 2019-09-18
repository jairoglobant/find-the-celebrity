package com.findthecelebrity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.findthecelebrity.dataproviders.database.DatabaseDataProvider;
import com.findthecelebrity.dataproviders.database.PersonRepository;
import com.findthecelebrity.domain.Celebrity;
import com.findthecelebrity.domain.DataProvider;

@Configuration
public class AppConfiguration {
	
	@Bean
	public DataProvider databaseDataProvider(PersonRepository personRepository) {
		return new DatabaseDataProvider(personRepository);
	}
	
	@Bean
	public Celebrity celebrity(DataProvider databaseDataProvider) {
		return new Celebrity(databaseDataProvider);
	}
	
}
