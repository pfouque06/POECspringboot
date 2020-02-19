package com.firstspringstartproject03.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.firstspringstartproject03.dao.UserRepository;
import com.firstspringstartproject03.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRestControllerTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testGetPersonnes() {
		User u1 = new User("u1", "u1");
		User u2 = new User("u2", "u2");
		entityManager.persist(u1);
		entityManager.persist(u2);
		
		List<User> usersDB = userRepository.findAll();
		List<User> users = new ArrayList<>();
		
		for (User u : usersDB) {
			users.add(u);
		}
		assertThat(users.size()).isEqualTo(2);
		//fail("Not yet implemented");
	}

	@Test
	void testGetPersonneById() {
		User user = new User("user", "user");
		User usersDB = entityManager.persist(user);
		User usersID = userRepository.getOne(usersDB.getId());
		assertEquals(usersDB, usersID);
		assertThat(usersID.equals(usersDB));
		//fail("Not yet implemented");
	}

	@Test
	void testSave() {
		User user = new User("user", "user");
		User usersDB = entityManager.persist(user);
		User usersID = userRepository.getOne(usersDB.getId());
		assertEquals(usersDB, usersID);
		assertThat(usersID.equals(usersDB));
		//fail("Not yet implemented");
	}

	@Test
	void testUpdatePersonne() {
		User user = new User("user", "user");
		entityManager.persist(user);
		User usersDB = userRepository.getOne(user.getId());
		usersDB.setNom("USERO");
		entityManager.persist(usersDB);
		assertThat(usersDB.getNom()).isEqualTo("USERO");
		//fail("Not yet implemented");
	}

	@Test
	void testDeletePersonne() {
		User u1 = new User("u1", "u1");
		User u2 = new User("u2", "u2");
		User persists = entityManager.persist(u1);
		entityManager.persist(u2);
		entityManager.remove(persists);
		
		List<User> usersDB = userRepository.findAll();
		List<User> users = new ArrayList<>();
		
		for (User user : usersDB) {
			users.add(user);
		}
		assertThat(users.size()).isEqualTo(1);
		//fail("Not yet implemented");
	}

}
