package com.restapi.many2many.controller;

import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.many2many.dao.ReaderRepository;
import com.restapi.many2many.dao.StudentRepository;
import com.restapi.many2many.entities.Reader;
import com.restapi.many2many.entities.Student;
import com.restapi.many2many.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private ReaderRepository readerRepository;
	@Autowired
	private StudentRepository studentRepository;

	@PostMapping()
	public Student createStudent(@Valid @RequestBody Student student) {
		return this.studentRepository.save(student);
	}

	@GetMapping("/{id}")
	public Student getStudent(@PathVariable Long id) {
		return this.studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", id));
	}

	@GetMapping()
	public Page<Student> getStudents(Pageable pageable) {
		return this.studentRepository.findAll(pageable);
	}

	@PutMapping()
	public Student updateStudent(@Valid @RequestBody Student student) {
		return this.studentRepository.findById(student.getId()).map((toUpdate) -> {
			toUpdate.setFirstName(student.getFirstName());
			toUpdate.setLastName(student.getLastName());
			toUpdate.setGpa(student.getGpa());
			return this.studentRepository.save(toUpdate);
		}).orElseThrow(() -> new ResourceNotFoundException("Student", student.getId()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteStudent(@PathVariable Long id) {
		return this.studentRepository.findById(id).map((toDelete) -> {
			this.studentRepository.delete(toDelete);
			return ResponseEntity.ok("Student id " + id + " deleted");
		}).orElseThrow(() -> new ResourceNotFoundException("Student", id));
	}

	@GetMapping("/{id}/readers")
	public Set<Reader> getReaders(@PathVariable Long id) {
		return this.studentRepository.findById(id).map((student) -> {
			return student.getReaders();
		}).orElseThrow(() -> new ResourceNotFoundException("Student", id));
	}
	
	@PostMapping("/{studentId}/readers/{readerId}")
	public Reader setStudentsbyId(
			@PathVariable Long readerId,
			@PathVariable Long studentId) {
		Student student = this.studentRepository
				.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", studentId));		
		
		Reader reader = this.readerRepository
				.findById(readerId)
				.map((toAdd) -> {
					Set<Reader> readers = student.getReaders();
					readers.add(toAdd);
					student.setReaders(readers);
					return this.readerRepository.save(toAdd); })
				.orElseThrow(() -> new ResourceNotFoundException("Reader", readerId));		
		
		return reader;
	}
}