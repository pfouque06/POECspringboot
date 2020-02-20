package com.restapi.many2one.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.many2one.dao.PersonneRepository;
import com.restapi.many2one.entities.Personne;
import com.restapi.many2one.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class PersonneController {
	@Autowired
	private PersonneRepository personneRepository;

	@GetMapping("/personnes")
	public List<Personne> getPersonnes() {
		return personneRepository.findAll();
	}

	@GetMapping("/personnes/{id}")
	public ResponseEntity<Personne> getPersonneById(@PathVariable(value = "id") Long personneId)
			throws ResourceNotFoundException {
		Personne user = personneRepository.findById(personneId)
				.orElseThrow(() -> new ResourceNotFoundException("Personne not found :: " + personneId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/personnes")
	public Personne createPersonne(@Valid @RequestBody Personne personne) {
		return personneRepository.save(personne);
	}

	@PutMapping("/personnes/{id}")
	public ResponseEntity<Personne> updatePersonne(@PathVariable(value = "id") Long personneId,
			@Valid @RequestBody Personne userDetails) throws ResourceNotFoundException {
		Personne user = personneRepository.findById(personneId)
				.orElseThrow(() -> new ResourceNotFoundException("Personne not found :: " + personneId));
		user.setNom(userDetails.getNom());
		user.setPrenom(userDetails.getPrenom());
		user.setAge(userDetails.getAge());
		final Personne updatedUser = personneRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/personnes/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long personneId)
			throws ResourceNotFoundException {
		Personne instructor = personneRepository.findById(personneId)
				.orElseThrow(() -> new ResourceNotFoundException("Personne not found :: " + personneId));
		personneRepository.delete(instructor);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}