package com.firstspringstartproject02.dao;

import org.springframework.data.jpa.repository.*;

import com.firstspringstartproject02.model.*;

public interface AdresseRepository extends JpaRepository <Adresse, Long> {

//	List<Adresse> findByNomAndPrenom(String nom, String prenom);
//	List<Adresse> findByNom(String nom);
//	List<Adresse> findByPrenom(String prenom);
}
