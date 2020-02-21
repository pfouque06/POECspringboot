package com.restapi.many2many.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lecteurs")
public class Reader extends Person {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String salary;
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
//	@JoinTable(name = "reader_students",
//		joinColumns = { @JoinColumn(name = "reader_id") },
//		inverseJoinColumns = { @JoinColumn(name = "student_id") })
//	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToMany(cascade = CascadeType.ALL)
	//@JsonIgnore
	private Set<Student> students = new HashSet<>();

	// CONSTRUCTORS

	public Reader() {
	}

	public Reader(String firstName, String lastName, String string) {
		super(firstName, lastName);
		this.salary = string;
	}

	public Reader(String firstName, String lastName, String salary, Set<Student> students) {
		super(firstName, lastName);
		this.salary = salary;
		this.students = students;
	}

	// GETTERS AND SETTERS
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getSalary() { return salary; }
	public void setSalary(String salary) { this.salary = salary; }

	public Set<Student> getStudents() { return students; }
	public void setStudents(Set<Student> students) { this.students = students; }
}
