package com.firstspringstartproject02.service;

import java.util.List;

import com.firstspringstartproject02.model.Commentaire;

public interface CommentaireService {
	
	List<Commentaire> getAllCommentaire();

	Commentaire getCommentaireById(Long id);

	Commentaire saveOrUpdateCommentaire(Commentaire commentaire);

	void deleteCommentaire(Long id);


}
