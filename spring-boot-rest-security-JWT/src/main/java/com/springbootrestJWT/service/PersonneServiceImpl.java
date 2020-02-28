package com.springbootrestJWT.service;

import java.util.Collection;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootrestJWT.dao.PersonneRepository;
import com.springbootrestJWT.model.Personne;

@Service(value = "personneService")
public class PersonneServiceImpl implements PersonneService {
	
    @Autowired
    private PersonneRepository personneRepository;

	@Override
	public Collection<Personne> getAllPersonne() {
		return IteratorUtils.toList(personneRepository.findAll().iterator());
	}

	@Override
	public Personne getPersonneById(Long id) {
		return personneRepository.findByNum(id);
	}

	@Override
	@Transactional(readOnly=false)
	public Personne saveOrUpdatePersonne(Personne personne) {
		return personneRepository.save(personne);
	}

	@Override
	@Transactional(readOnly=false)
	public void deletePersonne(Long id) {
		personneRepository.deleteById(id);
		
	}

	

}
