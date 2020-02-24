package com.firstspringstartproject02.controller;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstspringstartproject02.exceptions.ResourceNotFoundException;
import com.firstspringstartproject02.model.Personne;
import com.firstspringstartproject02.service.PersonneService;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonneRestController.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class PersonneRestController {

	/** The personne service. */
	@Autowired
	private PersonneService personneService;

	/**
	 * Gets the personnes.
	 *
	 * @return the personnes
	 */
	@GetMapping(value = "/personnes")
	public Collection<Personne> getPersonnes() {
		Collection<Personne> personnes = personneService.getAllPersonne();
		return personnes;
	}

	/**
	 * Gets the personne by id.
	 *
	 * @param id the id
	 * @return the personne by id
	 */
	@GetMapping(value = "/personnes/{id}")
	public ResponseEntity<Personne> getPersonneById(@PathVariable Long id) {

		Personne personne = personneService.getPersonneById(id);
		if (personne == null) {

			throw new ResourceNotFoundException("Personne not found : " + id);
		}

		return ResponseEntity.ok().body(personne);

	}

	/**
	 * Save.
	 *
	 * @param personne the personne
	 * @return the personne
	 */
	@PostMapping(value = "/personnes", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public Personne save(@Valid @RequestBody Personne personne) {

		return personneService.saveOrUpdatePersonne(personne);

	}

	/**
	 * Update personne.
	 *
	 * @param id the id
	 * @param personne the personne
	 * @return the response entity
	 */
	@PutMapping("/personnes/{id}")
	@Transactional
	public ResponseEntity<Personne> updatePersonne(@PathVariable(value = "id") Long id,
			@RequestBody Personne personne) {
		Personne personneToUpdate = personneService.getPersonneById(id);
		if (personneToUpdate == null) {
			throw new ResourceNotFoundException("Personne not found : " + id);
		}
		personneToUpdate.setNom(personne.getNom());
		personneToUpdate.setPrenom(personne.getPrenom());
		Personne personneUpdated = personneService.saveOrUpdatePersonne(personneToUpdate);
		return new ResponseEntity<Personne>(personneUpdated, HttpStatus.OK);
	}

	/**
	 * Delete personne.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/personnes/{id}")
	@Transactional
	public void deletePersonne(@PathVariable(value = "id") Long id) {
		Personne personne = personneService.getPersonneById(id);
		if (personne == null) {
			throw new ResourceNotFoundException("Personne not found : " + id);
		}

		personneService.deletePersonne(id);
	}

}
