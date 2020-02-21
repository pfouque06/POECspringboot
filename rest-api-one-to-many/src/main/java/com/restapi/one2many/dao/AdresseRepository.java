package com.restapi.one2many.dao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.restapi.one2many.entities.Adresse;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

	//List<Adresse> findByPersonneId(Long personneId);
	//Optional<Adresse> findByIdAndPersonneId(Long id, Long personneId);
}
