package com.restapi.many2one.controller;

import java.util.List;
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

import com.restapi.many2one.dao.AdresseRepository;
import com.restapi.many2one.dao.PersonneRepository;
import com.restapi.many2one.entities.Adresse;
import com.restapi.many2one.entities.Personne;
import com.restapi.many2one.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class AdresseController {
	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private PersonneRepository personneRepository;

	@GetMapping("/personnes/{personneId}/adresses")
	public List<Adresse> getAdressesByPersonne(@PathVariable("personneId") Long personneId) {
		return adresseRepository.findByPersonneId(personneId);
	}

	@PostMapping("/personnes/{personneId}/adresses")
	public Adresse save(@PathVariable(value = "personneId") Long personneId, @Valid @RequestBody Adresse adresse)
			throws ResourceNotFoundException {

		// save adresse
		Adresse adresseDB = personneRepository
							.findById(personneId)
							.map(personne -> {
								adresse.setPersonne(personne);
								return adresseRepository.save(adresse);
							})
							.orElseThrow(() -> new ResourceNotFoundException("Personne " +personneId+" not found"));
		
		// add adresse to Personnes list of adresses
		Personne personne = personneRepository.findById(personneId).get();
		List<Adresse> adresses = personne.getAdresses();
		adresses.add(adresse);
		personne.setAdresses(adresses);
		personneRepository.save(personne);
		return adresseDB;
	}

	@PutMapping("/personnes/{personneId}/adresses/{adresseId}")
	public Adresse updateAdresse(@PathVariable(value = "instructorId") Long personneId,
			@PathVariable(value = "adresseId") Long adresseId, @Valid @RequestBody Adresse adresseRequest)
			throws ResourceNotFoundException {
		if (!personneRepository.existsById(personneId)) {
			throw new ResourceNotFoundException("Personne "+personneId+" not found");
		}
		return adresseRepository
				.findById(adresseId)
				.map(adresse -> {
					adresse.setRue(adresseRequest.getRue());
					adresse.setVille(adresseRequest.getVille());
					adresse.setCodePostal(adresseRequest.getCodePostal());
					return adresseRepository.save(adresse);
				}).orElseThrow(() -> new ResourceNotFoundException("Adresse " +adresseId+" not found"));
	}

	@DeleteMapping("/personnes/{personneId}/adresses/{adresseId}")
	public ResponseEntity<?> deleteAdresse(@PathVariable(value = "personneId") Long personneId,
			@PathVariable(value = "adresseId") Long adresseId) throws ResourceNotFoundException {
		return adresseRepository
				.findByIdAndPersonneId(adresseId, personneId)
				.map(adresse -> {
					adresseRepository.delete(adresse);
					return ResponseEntity.ok().build();
				})
				.orElseThrow(() -> new ResourceNotFoundException("Adresse not found with id " + adresseId + " and personneId " + personneId));
	}
}