package com.findthecelebrity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.findthecelebrity.database.PersonRepository;
import com.findthecelebrity.domain.Celebrity;

@Configuration
public class AppConfiguration {
	
	@Bean
	public Celebrity celebrity(PersonRepository personRepository) {
		return new Celebrity(personRepository);
	}
	
}
