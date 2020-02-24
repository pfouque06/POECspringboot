package com.firstspringstartproject02.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstspringstartproject02.model.Commentaire;
import com.firstspringstartproject02.service.CommentaireService;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentaireRestController.class)
class CommentaireRestControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The commentaire service. */
	@MockBean
	private CommentaireService commentaireService;

	@Test
	void testGetPersonnes() throws Exception {
		Commentaire c1 = new Commentaire("c1", "c1", "c1", "c1");
		Commentaire c2 = new Commentaire("c2", "c2", "c2", "c2");		
		List<Commentaire> cList = new ArrayList<>();
		cList.add(c1);
		cList.add(c2);
		
		Mockito.when(commentaireService.getAllCommentaire()).thenReturn(cList);
		
		String URI = "/api/v1/commentaires";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(cList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

	}

	@Test
	void testGetPersonneById() throws Exception {

		Commentaire mockC = new Commentaire("c1", "c1", "c1", "c1");

		Mockito.when(commentaireService.getCommentaireById(Mockito.anyLong())).thenReturn(mockC);

		String URI = "/api/v1/commentaires/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockC);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	void testSave() throws Exception {

		Commentaire mockC = new Commentaire("c1", "c1", "c1", "c1");

		String inputInJson = this.mapToJson(mockC);

		String URI = "/api/v1/commentaires";

		Mockito.when(commentaireService.saveOrUpdateCommentaire(Mockito.any(Commentaire.class))).thenReturn(mockC);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);

	}

	@Test
	void testUpdatePersonne() throws Exception {
		
		//Commentaire mockC = new Commentaire("c1", "c1", "c1", "c1");
		Commentaire mockC = new Commentaire(1L, "c1", "c1", "c1", "c1");
		commentaireService.saveOrUpdateCommentaire(mockC);
		
		Mockito.when(commentaireService.getCommentaireById(Mockito.anyLong())).thenReturn(mockC);
					      
		Commentaire cFromDB = commentaireService.getCommentaireById(1L);
		
		cFromDB.setLibelle("LIBELLE");	
		String inputInJson = this.mapToJson(cFromDB);
		String URI = "/api/v1/commentaires/1";
		Mockito.when(commentaireService.saveOrUpdateCommentaire(Mockito.any(Commentaire.class))).thenReturn(mockC);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);
	}

	@Test
	void testDeletePersonne() throws Exception {
		Commentaire mockC = new Commentaire(1L, "c1", "c1", "c1", "c1");
		commentaireService.saveOrUpdateCommentaire(mockC);

		Mockito.when(commentaireService.getCommentaireById(Mockito.anyLong())).thenReturn(mockC);
		
		doNothing().when(commentaireService).deleteCommentaire(mockC.getId());
		
		String URI = "/api/v1/commentaires/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		verify(commentaireService, times(1)).getCommentaireById(mockC.getId());
		verify(commentaireService, times(1)).deleteCommentaire(mockC.getId());

	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
