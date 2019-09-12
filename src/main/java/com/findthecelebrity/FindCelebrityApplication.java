package com.findthecelebrity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.findthecelebrity.database.PersonEntity;
import com.findthecelebrity.database.PersonRepository;
import com.findthecelebrity.domain.Celebrity;

@SpringBootApplication
public class FindCelebrityApplication {

	private static final Logger log = LoggerFactory.getLogger(FindCelebrityApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(FindCelebrityApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(PersonRepository repository,Celebrity celebrity) {
		return (args) -> {
			log.info("********************* START ********************");
			savePeopleWithCelebrity(repository);
			//savePeopleWithoutCelebrity(repository);
			log.info("******************** The Celebrity is: "+celebrity.findTheCelebrity().getName());
		};
	}

	private void savePeopleWithoutCelebrity(PersonRepository repository) {
		PersonEntity keanu = new PersonEntity("Keanu Reevs",null);
		repository.save(keanu);
		
		PersonEntity jhon = new PersonEntity("Jhon",null);
		jhon.addKnownPerson(keanu);
		repository.save(jhon);
		
		PersonEntity matt = new PersonEntity("Matt",null);
		matt.addKnownPerson(keanu);
		matt.addKnownPerson(jhon);
		repository.save(matt);
		
		keanu.addKnownPerson(jhon);
		repository.save(keanu);

	}

	private void savePeopleWithCelebrity(PersonRepository repository) {
		PersonEntity keanu = new PersonEntity("Keanu Reevs",null);
		repository.save(keanu);
		
		PersonEntity jhon = new PersonEntity("Jhon",null);
		jhon.addKnownPerson(keanu);
		repository.save(jhon);
		
		PersonEntity matt = new PersonEntity("Matt",null);
		matt.addKnownPerson(keanu);
		matt.addKnownPerson(jhon);
		repository.save(matt);
		
	}

}
