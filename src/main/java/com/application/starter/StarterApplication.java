package com.application.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.application.starter.repositories.mongo")
@EnableElasticsearchRepositories(basePackages = "com.application.starter.repositories.elastic")
public class StarterApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(StarterApplication.class, args);
	}

}
