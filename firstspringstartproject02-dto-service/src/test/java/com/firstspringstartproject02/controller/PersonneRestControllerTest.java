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
import com.firstspringstartproject02.model.Personne;
import com.firstspringstartproject02.service.PersonneService;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonneRestControllerTest.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonneRestController.class)
class PersonneRestControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The personne service. */
	@MockBean
	private PersonneService personneService;

/** The personne. */
//	Personne personne = new Personne(1L, "admin", "admin");
	Personne personne = new Personne("admin", "admin");

	/**
	 * Test get personnes.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetPersonnes() throws Exception {
//		Personne mockPersonne1 = new Personne(1L, "admin", "admin");
//		Personne mockPersonne2 = new Personne(2L, "admin", "admin");		
		Personne mockPersonne1 = new Personne("admin", "admin");
		Personne mockPersonne2 = new Personne("admin", "admin");		
		List<Personne> personneList = new ArrayList<>();
		personneList.add(mockPersonne1);
		personneList.add(mockPersonne2);
		
		Mockito.when(personneService.getAllPersonne()).thenReturn(personneList);
		
		String URI = "/api/v1/personnes";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(personneList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

	}

	/**
	 * Test get personne by id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetPersonneById() throws Exception {

//		Personne mockPersonne = new Personne(1L, "admin", "admin");
		Personne mockPersonne = new Personne("admin", "admin");

		Mockito.when(personneService.getPersonneById(Mockito.anyLong())).thenReturn(mockPersonne);

		String URI = "/api/v1/personnes/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockPersonne);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	/**
	 * Test save.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testSave() throws Exception {

//		Personne mockPersonne = new Personne(1L, "admin", "admin");
		Personne mockPersonne = new Personne("admin", "admin");

		String inputInJson = this.mapToJson(mockPersonne);

		String URI = "/api/v1/personnes";

		Mockito.when(personneService.saveOrUpdatePersonne(Mockito.any(Personne.class))).thenReturn(mockPersonne);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);

	}

	/**
	 * Test update personne.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testUpdatePersonne() throws Exception {
		
		Personne mockPersonne = new Personne(1L, "admin", "admin");
		personneService.saveOrUpdatePersonne(mockPersonne);
		
//		Personne mockPersonne = new Personne("admin", "admin");
//		String inputInJson = this.mapToJson(mockPersonne);
//		String URI = "/api/v1/personnes";
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post(URI)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		//System.out.println("mock.num=" + mockPersonne.getNum());
		
		Mockito.when(personneService.getPersonneById(Mockito.anyLong())).thenReturn(mockPersonne);
					      
		Personne personneFromDB = personneService.getPersonneById(1L);
		
		personneFromDB.setNom("admino");	
		String inputInJson = this.mapToJson(personneFromDB);
		String URI = "/api/v1/personnes/1";
		Mockito.when(personneService.saveOrUpdatePersonne(Mockito.any(Personne.class))).thenReturn(mockPersonne);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(inputInJson);
	}

	/**
	 * Test delete personne.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDeletePersonne() throws Exception {
		Personne  mockPersonne = new Personne(1L, "admin", "admin");
//		Personne  mockPersonne = new Personne("admin", "admin");
		personneService.saveOrUpdatePersonne(mockPersonne);

		Mockito.when(personneService.getPersonneById(Mockito.anyLong())).thenReturn(mockPersonne);
		
		doNothing().when(personneService).deletePersonne(mockPersonne.getNum());
		
		String URI = "/api/v1/personnes/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		verify(personneService, times(1)).getPersonneById(mockPersonne.getNum());
		verify(personneService, times(1)).deletePersonne(mockPersonne.getNum());

	}

	/**
	 * Map to json.
	 *
	 * @param object the object
	 * @return the string
	 * @throws JsonProcessingException the json processing exception
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
