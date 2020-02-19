package com.firstspringstartproject02.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.firstspringstartproject02.dao.AdresseRepository;
import com.firstspringstartproject02.dao.PersonneRepository;
import com.firstspringstartproject02.exceptions.ResourceNotFoundException;
import com.firstspringstartproject02.model.Adresse;
import com.firstspringstartproject02.model.Personne;

@RestController
public class PersonneRestController {
	
	// in order to convert object to json
	//private static ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private PersonneRepository personneRepository;

	// ------------- Personne rest controller ------------------
	
	@GetMapping(value = "/personnes")
	public List<Personne> getPersonnes() {
		return personneRepository.findAll();
	}

	@GetMapping(value = "/personnes/{id}")
	public ResponseEntity<Personne> getPersonneById(@PathVariable Long id){
		Personne personne = personneRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Personne not found :: " + id));		
		return ResponseEntity.ok().body(personne);
	}

//	@GetMapping("/chercherPersonnes")
//	public Page<Personne> chercher(
//			@RequestParam(name="mc", defaultValue = "") String mc,
//			@RequestParam(name="page", defaultValue = "0") int page,
//			@RequestParam(name="size", defaultValue = "5") int size) {
//		Page<Personne> p = personneRepository.chercher(mc, new PageRequest(page, size));
//		return p;
//		
//	}
	
	@PostMapping("/personnes")
	public Personne save(@RequestBody Personne personne) {
		List<Adresse> adresses = personne.getAdresses();
		for (Adresse adresse : adresses) {
			Adresse adr = null;
			if (adresse.getNum() != null) {
				adr = adresseRepository.findById(adresse.getNum()).orElse(null);
				adresses.set(adresses.indexOf(adresse), adr);
			} else {
				adr = adresseRepository.save(adresse);
			}
		}
		return personneRepository.saveAndFlush(personne);
	}

	@PutMapping("/personnes/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable(value = "id") Long id, @RequestBody Personne personne)
    		throws ResourceNotFoundException {
    		Personne p = personneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personne not found :: " + id));
    		p.setNom(personne.getNom());
    		p.setPrenom(personne.getPrenom());
            final Personne updatedPersonne = personneRepository.save(p);
            return ResponseEntity.ok(updatedPersonne);
        }

	@DeleteMapping("/personnes/{id}")
	public Map <String, Boolean> deletePersonne(@PathVariable("id") long id) throws ResourceNotFoundException {
		Personne personne = personneRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Personne not found :: " + id));
		personneRepository.delete(personne);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
	}
	
	// ------------- Adresse rest controller ------------------
	
	@GetMapping("/adresses")
	public List<Adresse> getAdresses() {
		return adresseRepository.findAll();
	}
	
	@GetMapping("/adresses/{id}")
	public ResponseEntity<Adresse>  getAdresse(@PathVariable("id") long id) {
		Adresse adresse = adresseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Adresse not found :: " + id));	
		return ResponseEntity.ok().body(adresse);
	}
}