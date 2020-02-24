package com.firstspringstartproject02.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.firstspringstartproject02.dao.CommentaireRepository;
import com.firstspringstartproject02.model.Commentaire;

@RunWith(SpringRunner.class)
public class CommentaireServiceTest {

	@TestConfiguration // création des beans nécessaires pour les tests
	static class CommentaireServiceImplTestContextConfiguration {

		@Bean // bean de service
		public CommentaireService commentaireService() {
			return new CommentaireServiceImpl();
		}
	}
	
	@Autowired
	private CommentaireServiceImpl commentaireService;
	
	@MockBean // création d'un mockBean pour PersonneRepository
	private CommentaireRepository commentaireRepository;
	
	Commentaire commentaire = new Commentaire("c1", "c1", "c1", "c1");
	
	@Test
	public void testGetAllCommentaire() {
		Commentaire c1 = new Commentaire("c1", "c1", "c1", "c1");
		List<Commentaire> cList = new ArrayList<>();
		cList.add(c1);
		Mockito.when(commentaireRepository.findAll()).thenReturn(cList);
		List<Commentaire> cfromDB = commentaireService.getAllCommentaire();
		assertNotNull(cfromDB);
		assertEquals(cfromDB, cList);
		assertEquals(cfromDB.size(), cList.size());
		verify(commentaireRepository).findAll();;
	}

	@Test
	public void testGetCommentaireById() {
		Commentaire c1 = new Commentaire("c1", "c1", "c1", "c1");
//		Personne personneMock = new Personne(1L, "admin", "admin");
		Commentaire cMock = new Commentaire("c2", "c2", "c2", "c2");
		Mockito.when(commentaireRepository.save((c1))).thenReturn(cMock);
		Commentaire cSaved = commentaireService.saveOrUpdateCommentaire(c1);
		assertNotNull(cSaved);
		assertEquals(cMock.getLibelle(), cSaved.getLibelle());
		assertEquals(cMock.getDescription(), cSaved.getDescription());
		assertEquals(cMock.getSujet(), cSaved.getSujet());
		assertEquals(cMock.getDate(), cSaved.getDate());
		
		verify(commentaireRepository).save(c1);
	}

	@Test
	public void testSaveOrUpdateCommentaire() {
		Commentaire c1 = new Commentaire("c1", "c1", "c1", "c1");
		Optional<Commentaire> c1opt = Optional.of(c1);
		Mockito.when(commentaireRepository.findById(c1.getId())).thenReturn(c1opt);
		Commentaire cFromDB = commentaireService.getCommentaireById(c1.getId());
		assertNotNull(cFromDB);
		assertThat(cFromDB.getId(), is(c1.getId()));
		
		verify(commentaireRepository).findById(c1.getId());
	}

	@Test
	public void testDeleteCommentaire() {
		Commentaire cToUpdate = new Commentaire("c1", "c1", "c1", "c1");
		Commentaire cUpdated = new Commentaire("c2", "c2", "c2", "c2");
		Mockito.when(commentaireRepository.save((cToUpdate))).thenReturn(cUpdated);
		Commentaire cFromDB = commentaireService.saveOrUpdateCommentaire(cToUpdate);
		assertNotNull(cFromDB);
		assertEquals(cUpdated.getId(), cFromDB.getId());
		
		verify(commentaireRepository).save(commentaire);
	}

}
