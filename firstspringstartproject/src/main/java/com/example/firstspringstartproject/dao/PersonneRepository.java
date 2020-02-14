package com.example.firstspringstartproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.*;

import com.example.firstspringstartproject.model.*;

public interface PersonneRepository extends JpaRepository <Personne, Long> {

	List<Personne> findByNomAndPrenom(String nom, String prenom);
	List<Personne> findByNom(String nom);
	List<Personne> findByPrenom(String prenom);
}
