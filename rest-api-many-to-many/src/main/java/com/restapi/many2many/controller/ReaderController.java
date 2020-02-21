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
@RequestMapping("api/readers") // lecturers endpoint
public class ReaderController {
	@Autowired
	private ReaderRepository readerRepository;
	@Autowired
	private StudentRepository studentRepository;

	@PostMapping()
	public Reader createrReader(@Valid @RequestBody Reader reader) {
		return this.readerRepository.save(reader);
	}

	@GetMapping()
	public Page<Reader> getReaders(Pageable pageable) {
		return this.readerRepository.findAll(pageable);
	}

	@GetMapping("/{id}")
	public Reader getReader(@PathVariable Long id) {
		return this.readerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reader", id));
	}

	@PutMapping()
	public Reader updateReader(@Valid @RequestBody Reader reader) {
		return this.readerRepository.findById(reader.getId()).map((toUpdate) -> {
			toUpdate.setFirstName(reader.getFirstName());
			toUpdate.setLastName(reader.getLastName());
			toUpdate.setSalary(reader.getSalary());
			return this.readerRepository.save(toUpdate);
		}).orElseThrow(() -> new ResourceNotFoundException("Reader", reader.getId()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteReader(@PathVariable Long id) {
		return this.readerRepository.findById(id).map((toDelete) -> {
			this.readerRepository.delete(toDelete);
			return ResponseEntity.ok("Lecturer id " + id + " deleted");
		}).orElseThrow(() -> new ResourceNotFoundException("Reader", id));
	}

	@GetMapping("/{readerId}/students")
	public Set<Student> getStudents(@PathVariable Long readerId) {
		return this.readerRepository.findById(readerId).map((reader) -> {
			return reader.getStudents();
		}).orElseThrow(() -> new ResourceNotFoundException("Reader", readerId));
	}
	
	@PostMapping("/{readerId}/students/{studentId}")
	public Student setStudentsbyId(
			@PathVariable Long readerId,
			@PathVariable Long studentId) {
		Reader reader = this.readerRepository
				.findById(readerId)
				.orElseThrow(() -> new ResourceNotFoundException("Reader", readerId));		
		Student student = this.studentRepository
				.findById(studentId)
				.map((toAdd) -> {
					Set<Student> students = reader.getStudents();
					students.add(toAdd);
					reader.setStudents(students);
					return this.studentRepository.save(toAdd);
				}).orElseThrow(() -> new ResourceNotFoundException("Student", studentId));		
		return student;
	}

}