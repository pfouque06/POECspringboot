package com.example.firstspringstartproject.dao;

import org.springframework.data.jpa.repository.*;

import com.example.firstspringstartproject.model.*;

public interface CommentaireRepository extends JpaRepository <Commentaire, Integer> {

//	List<Personne> findByNomAndPrenom(String nom, String prenom);
//	List<Personne> findByNom(String nom);
//	List<Personne> findByPrenom(String prenom);
}
