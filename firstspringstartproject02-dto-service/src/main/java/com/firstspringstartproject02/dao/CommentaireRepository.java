package com.firstspringstartproject02.dao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.firstspringstartproject02.model.*;

@Repository
public interface CommentaireRepository extends JpaRepository <Commentaire, Long> {

//	Commentaire findById(Long id);

//	List<Personne> findByNomAndPrenom(String nom, String prenom);
//	List<Personne> findByNom(String nom);
//	List<Personne> findByPrenom(String prenom);
}
