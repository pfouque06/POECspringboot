package com.firstspringstartproject02.dao;

import org.springframework.data.jpa.repository.*;

import com.firstspringstartproject02.model.*;

public interface CommentaireRepository extends JpaRepository <Commentaire, Integer> {

//	List<Personne> findByNomAndPrenom(String nom, String prenom);
//	List<Personne> findByNom(String nom);
//	List<Personne> findByPrenom(String prenom);
}
