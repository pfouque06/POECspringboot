package com.firstspringstartproject02.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.firstspringstartproject02.exceptions.ResourceNotFoundException;
import com.firstspringstartproject02.model.Commentaire;
import com.firstspringstartproject02.service.CommentaireService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class CommentaireRestController {
	
	@Autowired
	private CommentaireService commentaireService;

	@GetMapping("/commentaires")
	@ResponseBody
	public List<Commentaire> getCommentaires() {
		return commentaireService.getAllCommentaire();
	}

	@GetMapping("/commentaires/{id}")
	public ResponseEntity<Commentaire>  getCommentaireById(@PathVariable("id") Long id) {
		Commentaire commentaire = commentaireService.getCommentaireById(id);
		if (commentaire == null) {

			throw new ResourceNotFoundException("Commentaire not found : " + id);
		}
		return ResponseEntity.ok().body(commentaire);
	}	

	@PostMapping(value = "/commentaires", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public Commentaire save(@Valid @RequestBody Commentaire commentaire) {
		return commentaireService.saveOrUpdateCommentaire(commentaire);
	}

	
	@PutMapping("/commentaires/{id}")
	@Transactional
	public ResponseEntity<Commentaire> updateCommentaire(@PathVariable("id") Long id, 
			@RequestBody Commentaire commentaire) {
		Commentaire commentaireToUpdate = commentaireService.getCommentaireById(id);
		if (commentaireToUpdate == null) {

			throw new ResourceNotFoundException("Commentaire not found : " + id);
		}
		commentaireToUpdate.setLibelle(commentaire.getLibelle());
		commentaireToUpdate.setDescription(commentaire.getDescription());
		commentaireToUpdate.setSujet(commentaire.getSujet());
		commentaireToUpdate.setDate(commentaire.getDate());
		
		Commentaire commentaireUpdated = commentaireService.saveOrUpdateCommentaire(commentaireToUpdate);

		return new ResponseEntity<Commentaire>(commentaireUpdated, HttpStatus.OK);
	}

	
	
	@DeleteMapping("/commentaires/{id}")
	@Transactional
	public void deleteCommentaire(@PathVariable("id") Long id) {
		Commentaire commentaireToUpdate = commentaireService.getCommentaireById(id);
		if (commentaireToUpdate == null) {

			throw new ResourceNotFoundException("Commentaire not found : " + id);
		}
		commentaireService.deleteCommentaire(id);
		return;
	}
	
}