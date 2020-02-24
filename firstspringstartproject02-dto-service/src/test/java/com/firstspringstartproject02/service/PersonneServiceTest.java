package com.firstspringstartproject02.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.firstspringstartproject02.dao.PersonneRepository;
import com.firstspringstartproject02.model.Personne;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonneServiceTest.
 */
@RunWith(SpringRunner.class)
public class PersonneServiceTest {

	/**
	 * The Class PersonneServiceImplTestContextConfiguration.
	 */
	@TestConfiguration // création des beans nécessaires pour les tests
	static class PersonneServiceImplTestContextConfiguration {

		/**
		 * Personne service.
		 *
		 * @return the personne service
		 */
		@Bean // bean de service
		public PersonneService personneService() {
			return new PersonneServiceImpl();
		}

	}

	/** The personne service. */
	@Autowired
	private PersonneServiceImpl personneService;

	/** The personne repository. */
	@MockBean // création d'un mockBean pour PersonneRepository
	private PersonneRepository personneRepository;

/** The personne. */
//	Personne personne = new Personne(1L, "admin", "admin");
	Personne personne = new Personne("admin", "admin");

	/**
	 * Test get personnes.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetPersonnes() throws Exception {
		Personne personne = new Personne("admin", "admin");
		List<Personne> allPersonnes = Arrays.asList(personne);
		Mockito.when(personneRepository.findAll()).thenReturn(allPersonnes);
		Collection<Personne> personnes = personneService.getAllPersonne();
		assertNotNull(personnes);
		assertEquals(personnes, allPersonnes);
		assertEquals(personnes.size(), allPersonnes.size());
		verify(personneRepository).findAll();
	}

	/**
	 * Test save.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSave() throws Exception {
		Personne personne = new Personne("admin", "admin");
//		Personne personneMock = new Personne(1L, "admin", "admin");
		Personne personneMock = new Personne("admin", "admin");
		Mockito.when(personneRepository.save((personne))).thenReturn(personneMock);
		Personne personneSaved = personneService.saveOrUpdatePersonne(personne);
		assertNotNull(personneSaved);
		assertEquals(personneMock.getNum(), personneSaved.getNum());
		assertEquals(personneMock.getNom(), personneSaved.getNom());
		
		verify(personneRepository).save(personne);

	}

	/**
	 * Test get personne by id.
	 */
	@Test
	public void testGetPersonneById() {
		Personne personne = new Personne("admin", "admin");
		Mockito.when(personneRepository.findByNum(personne.getNum())).thenReturn(personne);
		Personne personneFromDB = personneService.getPersonneById(personne.getNum());
		assertNotNull(personneFromDB);
		assertThat(personneFromDB.getNum(), is(personne.getNum()));
		
		verify(personneRepository).findByNum(personne.getNum());

	}

	/**
	 * Test delete.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDelete() throws Exception {
		Personne personne = new Personne("admin", "admin");
//		Personne personneMock = new Personne(1L, "admin", "admin");
		Personne personneMock = new Personne("admin", "admin");
		Mockito.when(personneRepository.save((personne))).thenReturn(personneMock);
		Personne personneSaved = personneService.saveOrUpdatePersonne(personne);
		assertNotNull(personneSaved);
		assertEquals(personneMock.getNum(), personneSaved.getNum());
		personneService.deletePersonne(personneSaved.getNum());
		
//		verify(personneRepository).deleteById(1L);
		verify(personneRepository).deleteById(personneSaved.getNum());

	}

	/**
	 * Test update personne.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testUpdatePersonne() throws Exception {
//		Personne personneToUpdate = new Personne(1L, "admin", "admin");
//		Personne personneUpdated = new Personne(1L, "admini", "admin");
		Personne personneToUpdate = new Personne("admin", "admin");
		Personne personneUpdated = new Personne("admini", "admin");
		Mockito.when(personneRepository.save((personneToUpdate))).thenReturn(personneUpdated);
		Personne personneFromDB = personneService.saveOrUpdatePersonne(personneToUpdate);
		assertNotNull(personneFromDB);
		assertEquals(personneUpdated.getNum(), personneFromDB.getNum());
		
		verify(personneRepository).save(personne);

	}
}
