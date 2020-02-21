package com.restapi.one2many.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.restapi.one2many.dao.AdresseRepository;
import com.restapi.one2many.dao.PersonneRepository;
import com.restapi.one2many.entities.Personne;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class PersonneControllerTest {

	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testGetPersonnes() {
		Personne t1 = new Personne("t1", "t1", 30);
		Personne t2 = new Personne("t2", "t2", 30);
		entityManager.persist(t1);
		entityManager.persist(t2);
		
		List<Personne> tableDB = personneRepository.findAll();
		List<Personne> table = new ArrayList<>();
		
		for (Personne t : tableDB) {
			table.add(t);
		}
		assertThat(table.size()).isEqualTo(2);
		//fail("Not yet implemented");
	}

	@Test
	void testGetPersonneById() {
		Personne t1 = new Personne("t1", "t1", 30);
		Personne tDB = entityManager.persist(t1);
		Personne t = personneRepository.getOne(tDB.getId());
		assertEquals(tDB, t);
		assertThat(t.equals(tDB));
		//fail("Not yet implemented");
	}

	@Test
	void testCreatePersonne() {
		Personne t1 = new Personne("t1", "t1", 30);
		Personne tDB = entityManager.persist(t1);
		Personne t = personneRepository.getOne(tDB.getId());
		assertEquals(tDB, t);
		assertThat(t.equals(tDB));
		//fail("Not yet implemented");
	}

	@Test
	void testUpdatePersonne() {
		Personne t1 = new Personne("t1", "t1", 30);
		entityManager.persist(t1);
		Personne tDB = personneRepository.getOne(t1.getId());
		tDB.setNom("NOM");
		entityManager.persist(tDB);
		assertThat(tDB.getNom()).isEqualTo("NOM");
		//fail("Not yet implemented");
	}

	@Test
	void testDeleteUser() {
		Personne t1 = new Personne("t1", "t1", 30);
		Personne t2 = new Personne("t2", "t2", 30);
		Personne tDB = entityManager.persist(t1);
		entityManager.persist(t2);
		entityManager.remove(tDB);
		
		List<Personne> tableDB = personneRepository.findAll();
		List<Personne> table = new ArrayList<>();
		
		for (Personne t : tableDB) {
			table.add(t);
		}
		assertThat(table.size()).isEqualTo(1);
		//fail("Not yet implemented");
	}

}
