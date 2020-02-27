package com.restapiangulardemo;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.restapiangulardemo.dao.UserRepository;
import com.restapiangulardemo.entity.User;

@SpringBootApplication
public class RestapiAngularDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiAngularDemoApplication.class, args);
	}

	// first time only
//	@Bean
//	CommandLineRunner init(UserRepository userRepository) {
//		return args -> {
//			Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
//				User user = new User(name, name.toLowerCase() + "@domain.com");
//				userRepository.save(user);
//				});
//			userRepository.findAll().forEach(System.out::println);
//		};
//	}
}