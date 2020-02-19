package com.firstspringstartproject03.dao;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import com.firstspringstartproject03.model.User;

public interface UserRepository extends JpaRepository <User, Long> {

	List<User> findByNomAndPrenom(String nom, String prenom);
	List<User> findByNom(String nom);
	List<User> findByPrenom(String prenom);
	
	//@Query("select p from Personne p where p.nom like %:x%")
	//public Page<Personne> chercher(@Param("x") String mc, Pageable pageable); //mc = mot clé
	
}
