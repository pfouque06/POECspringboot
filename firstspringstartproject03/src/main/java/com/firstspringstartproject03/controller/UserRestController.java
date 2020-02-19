package com.firstspringstartproject03.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.firstspringstartproject03.dao.UserRepository;
import com.firstspringstartproject03.exception.ResourceNotFoundException;
import com.firstspringstartproject03.model.User;

@RestController
public class UserRestController {
	
	// in order to convert object to json
	//private static ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private UserRepository userRepository;

	// ------------- User rest controller ------------------
	
	@GetMapping(value = "/users")
	public List<User> getPersonnes() {
		return userRepository.findAll();
	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<User> getPersonneById(@PathVariable Long id){
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));		
		return ResponseEntity.ok().body(user);
	}

//	@GetMapping("/chercherUsers")
//	public Page<User> chercher(
//			@RequestParam(name="mc", defaultValue = "") String mc,
//			@RequestParam(name="page", defaultValue = "0") int page,
//			@RequestParam(name="size", defaultValue = "5") int size) {
//		Page<User> p = userRepository.chercher(mc, new PageRequest(page, size));
//		return p;
//		
//	}
	
	@PostMapping("/users")
	public User save(@RequestBody User user) {
		return userRepository.saveAndFlush(user);
	}

	@PutMapping("/users/{id}")
    public ResponseEntity<User> updatePersonne(@PathVariable(value = "id") Long id, @RequestBody User user)
    		throws ResourceNotFoundException {
		User u = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));
    		u.setNom(user.getNom());
    		u.setPrenom(user.getPrenom());
            final User updatedUser = userRepository.save(u);
            return ResponseEntity.ok(updatedUser);
        }

	@DeleteMapping("/users/{id}")
	public Map <String, Boolean> deletePersonne(@PathVariable("id") long id) throws ResourceNotFoundException {
		User user = userRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Personne not found :: " + id));
		userRepository.delete(user);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
	}
	
}