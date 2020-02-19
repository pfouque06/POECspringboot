package com.example.firstspringstartproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstspringstartproject.dao.AdresseRepository;
import com.example.firstspringstartproject.dao.PersonneRepository;
import com.example.firstspringstartproject.model.Adresse;
import com.example.firstspringstartproject.model.Personne;

@RestController
public class PersonneRestController {
	
	// in order to convert object to json
	//private static ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private PersonneRepository personneRepository;

	@GetMapping("/personnes")
	@ResponseBody
	public List<Personne> getPersonnes() {
		return personneRepository.findAll();
		//List<Personne> personnes = personneRepository.findAll();
		//return mapper.writeValueAsString(personnes);
	}
	
	@GetMapping("/adresses")
	@ResponseBody
	public List<Adresse> getAdresses() {
		return adresseRepository.findAll();
		//List<Adresse> adresses = adresseRepository.findAll();
		//return mapper.writeValueAsString(adresses);
	}

	@GetMapping("/personnes/{id}")
	@ResponseBody
	public Personne  getPersonne(@PathVariable("id") long id) {
		return personneRepository.findById(id).orElse(null);
	}

	@GetMapping("/adresses/{id}")
	@ResponseBody
	public Adresse  getAdresse(@PathVariable("id") long id) {
		return adresseRepository.findById(id).orElse(null);
	}

//	@PostMapping("/addPersonne")
//	public String addPersonne(Personne personne, Model model) {
//		Personne personne2 = personneRepository.save(personne);
//		model.addAttribute("personne", personne2);
//		return "showPersonne";
//	}

//	@PostMapping("/personnes")
//	@ResponseBody
//	public String addPersonne(@RequestBody Personne personne) {
//		System.out.println(personne);
//		//return personneRepository.save(personne).toString();
//		Personne p = personneRepository.save(personne);
//		System.out.println(p);
//		return p.toString();
//	}

	@GetMapping("/chercherPersonnes")
	public Page<Personne> chercher(
			@RequestParam(name="mc", defaultValue = "") String mc,
			@RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="size", defaultValue = "5") int size) {
		Page<Personne> p = personneRepository.chercher(mc, new PageRequest(page, size));
		return p;
		
	}
	
	@PostMapping("/personnes")
	@ResponseBody
	public Personne addPersonne(@RequestBody Personne personne) {
		List<Adresse> adresses = personne.getAdresses();
		//System.out.println(personne);
		//adresses.forEach(System.out::println);
		for (Adresse adresse : adresses) {
			Adresse adr = null;
			if (adresse.getNum() != null) {
				adr = adresseRepository.findById(adresse.getNum()).orElse(null);
				adresses.set(adresses.indexOf(adresse), adr);
			} else {
				adr = adresseRepository.save(adresse);
			}
		}
		//System.out.println(personne);
		//adresses.forEach(System.out::println);
		return personneRepository.saveAndFlush(personne);
	}

	@PutMapping("/personnes/{id}")
	public Personne updatePersonne(@PathVariable("id") long id, @RequestBody Personne personne) {
		personne.setNum(id);
		// return personneRepository.save(personne);
		Personne p = personneRepository.save(personne);
		System.out.println(p);
		return p;
	}

	@DeleteMapping("/personnes/{id}")
	public void deletePersonne(@PathVariable("id") long id) {
		personneRepository.deleteById(id);
		return;
	}
	
	@DeleteMapping("/adresses/{id}")
	public void deleteAdresse(@PathVariable("id") long id) {
		adresseRepository.deleteById(id);
		return;
	}
}