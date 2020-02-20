package com.restapi.one2one.dao;

import org.springframework.data.jpa.repository.*;
import com.restapi.one2one.entities.Instructor;

public interface InstructorRepository extends JpaRepository <Instructor, Long> {

	//List<Instructor> findByNomAndPrenom(String nom, String prenom);
	//List<Instructor> findByNom(String nom);
	//List<Instructor> findByPrenom(String prenom);
	
	//@Query("select p from Personne p where p.nom like %:x%")
	//public Page<Personne> chercher(@Param("x") String mc, Pageable pageable); //mc = mot clé
	
}
