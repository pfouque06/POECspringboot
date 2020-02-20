package com.restapi.many2one.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.restapi.many2one.entities.Adresse;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

	List<Adresse> findByPersonneId(Long personneId);
	Optional<Adresse> findByIdAndPersonneId(Long id, Long personneId);
}
