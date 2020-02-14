package com.example.firstspringstartproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.firstspringstartproject.dao.AdresseRepository;
import com.example.firstspringstartproject.dao.CommentaireRepository;
import com.example.firstspringstartproject.dao.PersonneRepository;
import com.example.firstspringstartproject.model.Adresse;
import com.example.firstspringstartproject.model.Commentaire;
import com.example.firstspringstartproject.model.Personne;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CommentaireRestController {
	
	// in order to convert object to json
	private static ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private CommentaireRepository commentaireRepository;

	@GetMapping("/commentaires")
	@ResponseBody
	public List<Commentaire> getCommentaires() {
		return commentaireRepository.findAll();
	}
	
	@GetMapping("/commentaires/{id}")
	@ResponseBody
	public Commentaire  getCommentaire(@PathVariable("id") Integer id) {
		return commentaireRepository.findById(id).orElse(null);
	}

	@PostMapping("/commentaires")
	@ResponseBody
	public Commentaire addCommentaire(@RequestBody Commentaire commentaire) {
		return commentaireRepository.saveAndFlush(commentaire);
	}

	@PutMapping("/commentaires/{id}")
	public Commentaire updateCommentaire(@PathVariable("id") Integer id, @RequestBody Commentaire commentaire) {
		commentaire.setId(id);
		Commentaire c = commentaireRepository.save(commentaire);
		System.out.println(c);
		return c;
	}

	@DeleteMapping("/commentaires/{id}")
	public void deleteCommentaire(@PathVariable("id") Integer id) {
		commentaireRepository.deleteById(id);
		return;
	}
	
}