package com.restapi.one2many.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.restapi.one2many.dao.AdresseRepository;
import com.restapi.one2many.dao.PersonneRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class AdresseControllerTest {

	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testGetAdressesByPersonne() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAdresseByPersonne() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateAdresse() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteAdresse() {
		fail("Not yet implemented");
	}

}
