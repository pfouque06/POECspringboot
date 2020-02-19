package com.firstspringstartproject02.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.firstspringstartproject02.model.*;

public interface PersonneRepository extends JpaRepository <Personne, Long> {

	List<Personne> findByNomAndPrenom(String nom, String prenom);
	List<Personne> findByNom(String nom);
	List<Personne> findByPrenom(String prenom);
	
	//@Query("select p from Personne p where p.nom like %:x%")
	//public Page<Personne> chercher(@Param("x") String mc, Pageable pageable); //mc = mot clé
	
}
