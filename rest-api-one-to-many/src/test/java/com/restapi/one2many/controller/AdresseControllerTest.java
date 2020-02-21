package com.restapi.one2many.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.restapi.one2many.entities.Adresse;
import com.restapi.one2many.entities.Personne;

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
		Personne t1 = new Personne("t1", "t1", 30);
		List<Adresse> adresses = t1.getAdresses();
		Adresse a1 = new Adresse("a1", "a1", "a1");
		Adresse a2 = new Adresse("a2", "a2", "a2");
		adresses.add(a1);
		adresses.add(a2);
		t1.setAdresses(adresses);
		entityManager.persist(t1);
		//courseRepository.save(co1);
				
		Personne tDB = personneRepository.getOne(t1.getId());
		List<Adresse> tableDB = tDB.getAdresses();
		List<Adresse> table = new ArrayList<>();
		
		for (Adresse t : tableDB) {
			table.add(t);
		}
		assertThat(table.size()).isEqualTo(2);
		//fail("Not yet implemented");;
	}

	@Test
	void testGetAdresseByPersonne() {
		Personne t1 = new Personne("t1", "t1", 30);
		List<Adresse> adresses = t1.getAdresses();
		Adresse a1 = new Adresse("a1", "a1", "a1");
		Adresse a2 = new Adresse("a2", "a2", "a2");
		adresses.add(a1);
		adresses.add(a2);
		t1.setAdresses(adresses);
		entityManager.persist(t1);
		//courseRepository.save(co1);
				
		Personne tDB = personneRepository.getOne(t1.getId());
		List<Adresse> tableDB = tDB.getAdresses();
		List<Adresse> table = new ArrayList<>();
		
		for (Adresse t : tableDB) {
			table.add(t);
		}
		assertThat(table.size()).isEqualTo(2);
		//fail("Not yet implemented");;
	}

	@Test
	void testSave() {
		Personne t1 = new Personne("t1", "t1", 30);
		List<Adresse> adresses = t1.getAdresses();
		Adresse a1 = new Adresse("a1", "a1", "a1");
		adresses.add(a1);
//		Adresse a2 = new Adresse("a2", "a2", "a2");
//		adresses.add(a2);
		t1.setAdresses(adresses);
		entityManager.persist(t1);
		
	
		Personne tDB = personneRepository.getOne(t1.getId());
		List<Adresse> tableDB = tDB.getAdresses();
		List<Adresse> table = new ArrayList<>();
		for (Adresse t : tableDB) {
			table.add(adresseRepository.getOne(t.getId()));
			assertEquals(t, adresseRepository.getOne(t.getId()));
		}
		assertThat(table.equals(tableDB));
		//fail("Not yet implemented");
	}

	@Test
	void testUpdateAdresse() {
		Personne t3 = new Personne("t3", "t3", 30);
		List<Adresse> adresses = t3.getAdresses();
		Adresse a3 = new Adresse("a3", "a3", "a3");
		adresses.add(a3);
		t3.setAdresses(adresses);
		Personne tDB = entityManager.persist(t3);

		Adresse aDB = tDB.getAdresses().get(0);
		aDB.setVille("VILLE");
		adresses.set(0, aDB);
		tDB.setAdresses(adresses);
		entityManager.persist(tDB);

		List<Adresse> tableDB = personneRepository.getOne(tDB.getId()).getAdresses();
		List<Adresse> table = new ArrayList<>();
		for (Adresse t : tableDB) {
			table.add(adresseRepository.getOne(t.getId()));
			assertEquals(t, adresseRepository.getOne(t.getId()));
		}
		assertThat(table.equals(tableDB));
		//fail("Not yet implemented");
	}

	@Test
	void testDeleteAdresse() {
		Personne t3 = new Personne("t3", "t3", 30);
		List<Adresse> adresses = t3.getAdresses();
		Adresse a3 = new Adresse("a3", "a3", "a3");
		adresses.add(a3);
		t3.setAdresses(adresses);
		Personne tDB = entityManager.persist(t3);
		Adresse aDB = tDB.getAdresses().get(0);
				
		Adresse a4 = new Adresse("a4", "a4", "a4");
		adresses.add(a4);
		t3.setAdresses(adresses);
		entityManager.persist(t3);

		adresses = t3.getAdresses();
		adresses.remove(a3);
		entityManager.persist(t3);
		//entityManager.remove(aDB);  

		List<Adresse> tableDB = personneRepository.getOne(tDB.getId()).getAdresses();
		List<Adresse> table = new ArrayList<>();
		
		for (Adresse t : tableDB) {
			table.add(t);
		}
		assertThat(table.size()).isEqualTo(1);
		//fail("Not yet implemented");
	}

}
