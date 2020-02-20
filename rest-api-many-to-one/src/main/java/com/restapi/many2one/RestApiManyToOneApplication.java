package com.restapi.many2one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ActiveProfiles("prod")
public class RestApiManyToOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiManyToOneApplication.class, args);
	}

}
