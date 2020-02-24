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

import com.firstspringstartproject02.model.Commentaire;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentaireRepositoryTest {

	@Autowired
	CommentaireRepository commentaireRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	void testgetCommentaires() {

		Commentaire c1 = new Commentaire("c1", "c1", "c1", "c1");
		Commentaire c2 = new Commentaire("c2", "c2", "c2", "c2");
		List<Commentaire> cList = new ArrayList<>();
		cList.add(c1);
		cList.add(c2);

		entityManager.persist(c1);
		entityManager.persist(c2);

		List<Commentaire> cFromDb = commentaireRepository.findAll();

		assertEquals(cList, cFromDb);
		assertThat(cFromDb.equals(cList));
	}

	@Test
	void testGetCommentaireById() {
		Commentaire c = new Commentaire("c1", "c1", "c1", "c1");
		Commentaire cInDb = entityManager.persist(c);
		Commentaire cFromDb = commentaireRepository.getOne(cInDb.getId());
		assertEquals(cInDb, cFromDb);
		assertThat(cFromDb.equals(cInDb));
	}

	@Test
	void testSave() {

		Commentaire c = new Commentaire("c1", "c1", "c1", "c1");
		Commentaire cInDb = entityManager.persist(c);
		Commentaire cFromDb = commentaireRepository.getOne(cInDb.getId());
		assertThat(cFromDb.equals(cInDb));
	}

	@Test
	void testUpdateCommentaire() {

		Commentaire c = new Commentaire("c1", "c1", "c1", "c1");
		entityManager.persist(c);
		Commentaire cFromDb = commentaireRepository.getOne(c.getId());
		cFromDb.setLibelle("LIBELLE");
		entityManager.persist(cFromDb);
		assertThat(cFromDb.getLibelle()).isEqualTo("LIBELLE");
	}

	@Test
	void testDeleteCommentaire() {

		Commentaire c1 = new Commentaire("c1", "c1", "c1", "c1");
		Commentaire c2 = new Commentaire("c2", "c2", "c2", "c2");
		List<Commentaire> cList = new ArrayList<>();
		cList.add(c1);
		cList.add(c2);

		entityManager.persist(c1);
		entityManager.persist(c2);
		
		Commentaire cFromDb = commentaireRepository.getOne(c2.getId());
		entityManager.remove(cFromDb);

		assertThat(!cList.contains(cFromDb));
	}

}
