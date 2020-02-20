package com.restapi.one2one.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restapi.one2one.dao.InstructorRepository;
import com.restapi.one2one.entities.Instructor;
import com.restapi.one2one.exception.ResourceNotFoundException;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
public class InstructorController {
	@Autowired
	private InstructorRepository instructorRepository;

	@GetMapping("/instructors")
	public List<Instructor> getInstructors() {
		return instructorRepository.findAll();
	}

	@GetMapping("/instructors/{id}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable(value = "id") Long instructorId)
			throws ResourceNotFoundException {
		Instructor user = instructorRepository.findById(instructorId)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/instructors")
	public Instructor createUser(@Valid @RequestBody Instructor instructor) {
		return instructorRepository.save(instructor);
	}

	@PutMapping("/instructors/{id}")
	public ResponseEntity<Instructor> updateUser(@PathVariable(value = "id") Long instructorId,
			@Valid @RequestBody Instructor userDetails) throws ResourceNotFoundException {
		Instructor user = instructorRepository.findById(instructorId)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
		user.setEmail(userDetails.getEmail());
		final Instructor updatedUser = instructorRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/instructors/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long instructorId)
			throws ResourceNotFoundException {
		Instructor instructor = instructorRepository.findById(instructorId)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
		instructorRepository.delete(instructor);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}