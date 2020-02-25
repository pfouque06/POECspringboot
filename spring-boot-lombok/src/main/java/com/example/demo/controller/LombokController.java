package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Personne;

@Controller
public class LombokController {

	@GetMapping("/lombok")
	public String sayHello() {
		Personne personne = new Personne();
		personne.setNom("nom");
		personne.setPrenom("prenom");
		Personne personne2 = new Personne(1, "nom2", "prenom2");
		Personne personne3 = new Personne("nom3", "prenom3");
		System.out.println(personne.getNom().toUpperCase() + " " + personne.getPrenom());
		System.out.println(personne);
		System.out.println(personne2);
		System.out.println(personne3);
		return "lombok";
	}
}
