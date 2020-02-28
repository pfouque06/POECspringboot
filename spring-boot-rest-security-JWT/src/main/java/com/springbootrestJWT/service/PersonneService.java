package com.springbootrestJWT.service;

import java.util.Collection;

import com.springbootrestJWT.model.Personne;

// TODO: Auto-generated Javadoc
/**
 * The Interface PersonneService.
 */
public interface PersonneService {
	
	/**
	 * Gets the all personne.
	 *
	 * @return the all personne
	 */
	Collection<Personne> getAllPersonne();

	/**
	 * Gets the personne by id.
	 *
	 * @param id the id
	 * @return the personne by id
	 */
	Personne getPersonneById(Long id);

	/**
	 * Save or update personne.
	 *
	 * @param personne the personne
	 * @return the personne
	 */
	Personne saveOrUpdatePersonne(Personne personne);

	/**
	 * Delete personne.
	 *
	 * @param id the id
	 */
	void deletePersonne(Long id);


}
