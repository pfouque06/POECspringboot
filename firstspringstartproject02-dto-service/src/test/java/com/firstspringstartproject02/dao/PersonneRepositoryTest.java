package com.firstspringstartproject02.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.firstspringstartproject02.model.Personne;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonneRepositoryTest.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonneRepositoryTest {

	/** The personne repository. */
	@Autowired
	PersonneRepository personneRepository;

	/** The entity manager. */
	@Autowired
	private TestEntityManager entityManager;

	/**
	 * Test get personnes.
	 */
	@Test
	void testGetPersonnes() {

//		Personne personne1 = new Personne(15L, "admin", "admin");
//		Personne personne2 = new Personne(16L, "admin2", "admin2");
		Personne personne1 = new Personne("admin", "admin");
		Personne personne2 = new Personne("admin2", "admin2");
		List<Personne> personneList = new ArrayList<>();
		personneList.add(personne1);
		personneList.add(personne2);

		entityManager.persist(personne1);
		entityManager.persist(personne2);

		List<Personne> personnesFromDb = personneRepository.findAll();

		assertEquals(personneList, personnesFromDb);
		assertThat(personnesFromDb.equals(personneList));
	}

	/**
	 * Test get personne by id.
	 */
	@Test
	void testGetPersonneById() {
//		Personne personne = new Personne(1L, "admin", "admin");
		Personne personne = new Personne("admin", "admin");
		Personne personneSavedInDb = entityManager.persist(personne);
		Personne personneFromDb = personneRepository.getOne(personneSavedInDb.getNum());
		assertEquals(personneSavedInDb, personneFromDb);
		assertThat(personneFromDb.equals(personneSavedInDb));
	}

	/**
	 * Test save.
	 */
	@Test
	void testSave() {

//		Personne personne = new Personne(1L, "admin", "admin");
		Personne personne = new Personne("admin", "admin");
		Personne SavedInDb = entityManager.persist(personne);
		Personne getFromDb = personneRepository.getOne(SavedInDb.getNum());
		assertThat(getFromDb).isEqualTo(SavedInDb);
	}

	/**
	 * Test update personne.
	 */
	@Test
	void testUpdatePersonne() {

//		Personne personne = new Personne(1L, "admin", "admin");
		Personne personne = new Personne("admin", "admin");
		entityManager.persist(personne);
		Personne getFromDb = personneRepository.getOne(personne.getNum());
		getFromDb.setNom("admino");
		entityManager.persist(getFromDb);
		assertThat(getFromDb.getNom()).isEqualTo("admino");
	}

	/**
	 * Test delete personne.
	 */
	@Test
	void testDeletePersonne() {

//		Personne personne1 = new Personne(3L, "admin", "admin");
//		Personne personne2 = new Personne(4L, "admin2", "admin2");
		Personne personne1 = new Personne("admin", "admin");
		Personne personne2 = new Personne("admin2", "admin2");
		List<Personne> personneList = new ArrayList<>();
		personneList.add(personne1);
		personneList.add(personne2);

		entityManager.persist(personne1);
		entityManager.persist(personne2);
		Personne getFromDb = personneRepository.getOne(personne2.getNum());
		entityManager.remove(getFromDb);

		assertThat(!personneList.contains(getFromDb));
	}

}
