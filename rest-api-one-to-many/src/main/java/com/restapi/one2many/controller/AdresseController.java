package com.restapi.one2many.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.one2many.dao.AdresseRepository;
import com.restapi.one2many.dao.PersonneRepository;
import com.restapi.one2many.entities.Adresse;
import com.restapi.one2many.entities.Personne;
import com.restapi.one2many.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class AdresseController {
	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private PersonneRepository personneRepository;
	
	@GetMapping("/adresses")
	public List<Adresse> getAdresses() {
		
		return adresseRepository.findAll();
	}

	@GetMapping("/adresses/{adresseId}")
	public Adresse getAdressesById(
			@PathVariable("adresseId") Long adresseId) throws ResourceNotFoundException {
		
		if (!adresseRepository.existsById(adresseId))
			throw new ResourceNotFoundException("Adresse "+adresseId+" not found");
		
		Adresse adresse = adresseRepository.getOne(adresseId);
		System.out.println(adresse);
		//return null;
		return adresse;
	}

	@GetMapping("/personnes/{personneId}/adresses")
	public List<Adresse> getAdressesByPersonne(
			@PathVariable("personneId") Long personneId) {

		if (!personneRepository.existsById(personneId))
			throw new ResourceNotFoundException("Personne "+personneId+" not found");
		
		//return adresseRepository.findByPersonneId(personneId);
		return personneRepository.getOne(personneId).getAdresses();
	}

	@GetMapping("/personnes/{personneId}/adresses/{adresseId}")
	public Adresse getAdresseByPersonne(
			@PathVariable("personneId") Long personneId,
			@PathVariable(value = "adresseId") Long adresseId) throws ResourceNotFoundException {
		if (!personneRepository.existsById(personneId))
			throw new ResourceNotFoundException("Personne "+personneId+" not found");

		if (!adresseRepository.existsById(adresseId))
			throw new ResourceNotFoundException("Adresse "+adresseId+" not found");
		
		Adresse adresse = adresseRepository.getOne(adresseId);
		Personne personne = personneRepository.getOne(personneId);
		List<Adresse> adresses = personne.getAdresses();
		if (!adresses.contains(adresse))
				throw new ResourceNotFoundException("Adresse "+adresseId+" does not belong to Personne "+personneId);
		
		return adresse;
	}
	
	@PostMapping("/personnes/{personneId}/adresses")
	public Adresse save(
			@PathVariable(value = "personneId") Long personneId,
			@Valid @RequestBody Adresse adresse) throws ResourceNotFoundException {
		if (!personneRepository.existsById(personneId))
			throw new ResourceNotFoundException("Personne "+personneId+" not found");

		Personne personne = personneRepository.getOne(personneId);
		List<Adresse> adresses = personne.getAdresses();
		adresse = adresseRepository.save(adresse);
		adresses.add(adresse);

		personne.setAdresses(adresses);
		personneRepository.save(personne);
		return adresse;
	}

	@PutMapping("/personnes/{personneId}/adresses/{adresseId}")
	public Adresse updateAdresse(
			@PathVariable(value = "personneId") Long personneId,
			@PathVariable(value = "adresseId") Long adresseId,
			@Valid @RequestBody Adresse adresseRequest)
			throws ResourceNotFoundException {
		if (!personneRepository.existsById(personneId))
			throw new ResourceNotFoundException("Personne "+personneId+" not found");

		if (!adresseRepository.existsById(adresseId))
			throw new ResourceNotFoundException("Adresse "+adresseId+" not found");
		
		Adresse adresse = adresseRepository.getOne(adresseId);
		Personne personne = personneRepository.getOne(personneId);
		List<Adresse> adresses = personne.getAdresses();
		if (!adresses.contains(adresse))
				throw new ResourceNotFoundException("Adresse "+adresseId+" does not belong to Personne "+personneId);

		adresse.setRue(adresseRequest.getRue());
		adresse.setVille(adresseRequest.getVille());
		adresse.setCodePostal(adresseRequest.getCodePostal());
		Adresse adrDB = adresseRepository.save(adresse);
		
		adresses.set(adresses.indexOf(adresse), adrDB);
		personne.setAdresses(adresses);
		personneRepository.save(personne);
		
		return adresseRepository.save(adresse);
	}

	@DeleteMapping("/personnes/{personneId}/adresses/{adresseId}")
	public Map<String, Boolean> deleteAdresse(
			@PathVariable(value = "personneId") Long personneId,
			@PathVariable(value = "adresseId") Long adresseId) throws ResourceNotFoundException {
		if (!personneRepository.existsById(personneId))
			throw new ResourceNotFoundException("Personne "+personneId+" not found");

		if (!adresseRepository.existsById(adresseId))
				throw new ResourceNotFoundException("Adresse "+adresseId+" not found");
		
		Adresse adresse = adresseRepository.getOne(adresseId);
		Personne personne = personneRepository.getOne(personneId);
		List<Adresse> adresses = personne.getAdresses();
		if (!adresses.contains(adresse))
				throw new ResourceNotFoundException("Adresse "+adresseId+" does not belong to Personne "+personneId);
		adresses.remove(adresse);
		personne.setAdresses(adresses);
		personneRepository.save(personne);
		adresseRepository.delete(adresse);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}