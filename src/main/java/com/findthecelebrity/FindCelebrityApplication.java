package com.findthecelebrity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.findthecelebrity.dataproviders.database.PersonRepository;
import com.findthecelebrity.domain.BusinessException;
import com.findthecelebrity.domain.Celebrity;
import com.findthecelebrity.util.StorePeopleInformation;

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
			StorePeopleInformation.storePeopleWithCelebrity(repository); 
			//StorePeopleInformation.storePeopleWithoutCelebrity(repository);
			try {
				log.info("******************** The Celebrity is: "+celebrity.findTheCelebrity().getName());
			} catch (BusinessException e) {
				log.error("******************** Error executing the proccess: "+e.getMessage(),e);
			}
		};
	}

}
