package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Personne;
import com.example.demo.service.PersonneService;

@RestController
public class PersonneRestController {
	
	@Autowired
	private PersonneService personneService;

    @RequestMapping("/personnes")
    public List<Personne> getPersonnes() {
    	
    	List<Personne> personneList = personneService.findAll();
        
        return personneList;
    }

}
