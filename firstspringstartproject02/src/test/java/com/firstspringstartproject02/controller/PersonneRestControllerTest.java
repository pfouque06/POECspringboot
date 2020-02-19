package com.firstspringstartproject02.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.firstspringstartproject02.dao.PersonneRepository;
import com.firstspringstartproject02.model.Personne;

@RunWith(SpringRunner.class)
@DataJpaTest
class PersonneRestControllerTest {

	@Autowired
	private PersonneRepository personneRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testGetPersonnes() {
		Personne p1 = new Personne("toto", "toto");
		Personne p2 = new Personne("tutu", "tutu");
		entityManager.persist(p1);
		entityManager.persist(p2);
		
		List<Personne> personnesDB = personneRepository.findAll();
		List<Personne> personnesLocal = new ArrayList<>();
		
		for (Personne p : personnesDB) {
			personnesLocal.add(p);
		}
		assertThat(personnesLocal.size()).isEqualTo(2);
		//fail("Not yet implemented");
	}

	@Test
	void testGetPersonneById() {
		Personne personne = new Personne("admin", "admin");
		Personne personneSaveInDB = entityManager.persist(personne);
		Personne personneFromDB = personneRepository.getOne(personneSaveInDB.getNum());
		assertEquals(personneSaveInDB, personneFromDB);
		assertThat(personneFromDB.equals(personneSaveInDB));
		//fail("Not yet implemented");
	}

	@Test
	void testSave() {
		Personne personne = new Personne("admin", "admin");
		Personne personneSaveInDB = entityManager.persist(personne);
		Personne personneFromDB = personneRepository.getOne(personneSaveInDB.getNum());
		assertEquals(personneSaveInDB, personneFromDB);
		assertThat(personneFromDB.equals(personneSaveInDB));
		//fail("Not yet implemented");
	}

	@Test
	void testUpdatePersonne() {
		Personne personne = new Personne("admin", "admin");
		entityManager.persist(personne);
		Personne personneFromDB = personneRepository.getOne(personne.getNum());
		personneFromDB.setNom("admino");
		entityManager.persist(personneFromDB);
		assertThat(personneFromDB.getNom()).isEqualTo("admino");
		//fail("Not yet implemented");
	}

	@Test
	void testDeletePersonne() {
		Personne p1 = new Personne("toto", "toto");
		Personne p2 = new Personne("tutu", "tutu");
		Personne persists = entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.remove(persists);
		
		List<Personne> personnesDB = personneRepository.findAll();
		List<Personne> personnesLocal = new ArrayList<>();
		
		for (Personne p : personnesDB) {
			personnesLocal.add(p);
		}
		assertThat(personnesLocal.size()).isEqualTo(1);
		//fail("Not yet implemented");
	}

}
