package com.restapi.one2many.dao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.restapi.one2many.entities.Personne;

@Repository
public interface PersonneRepository extends JpaRepository <Personne, Long> {

	//List<Adresse> findByPersonneId(Long personneId);
	//Optional<Adresse> findByIdAndPersonneId(Long id, Long personneId);
	
	//List<Instructor> findByNomAndPrenom(String nom, String prenom);
	//List<Instructor> findByNom(String nom);
	//List<Instructor> findByPrenom(String prenom);
	
	//@Query("select p from Personne p where p.nom like %:x%")
	//public Page<Personne> chercher(@Param("x") String mc, Pageable pageable); //mc = mot clé
	
}
