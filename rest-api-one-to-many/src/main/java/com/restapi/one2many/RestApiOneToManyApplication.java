package com.restapi.one2many;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ActiveProfiles("prod")
public class RestApiOneToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiOneToManyApplication.class, args);
	}

}
