package com.restapi.many2many;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestApiManyToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiManyToManyApplication.class, args);
	}

}
