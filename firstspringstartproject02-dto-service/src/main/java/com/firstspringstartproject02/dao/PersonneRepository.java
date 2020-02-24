package com.firstspringstartproject02.dao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.firstspringstartproject02.model.*;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersonneRepository.
 */
@Repository
public interface PersonneRepository extends JpaRepository <Personne, Long> {

	/**
	 * Find by num.
	 *
	 * @param num the num
	 * @return the personne
	 */
	Personne findByNum(Long num);

	//List<Personne> findByNomAndPrenom(String nom, String prenom);
	//List<Personne> findByNom(String nom);
	//List<Personne> findByPrenom(String prenom);
	
	//@Query("select p from Personne p where p.nom like %:x%")
	//public Page<Personne> chercher(@Param("x") String mc, Pageable pageable); //mc = mot clé
	
}
