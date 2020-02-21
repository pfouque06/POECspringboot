package com.restapi.many2many.entities;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "students")
public class Student extends Person {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Float gpa;
	
	//@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "students")
//	@JoinTable(name = "readers_students",
//			joinColumns = { @JoinColumn(name = "student_id") },
//			inverseJoinColumns = { @JoinColumn(name = "reader_id") })
//	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "students")
	@JsonIgnore
	private Set<Reader> readers = new HashSet<>();

	// CONSTRUCTORS

	public Student() {}

	public Student(String firstName, String lastName, Float gpa) {
		super(firstName, lastName);
		this.gpa = gpa;
	}

	public Student(String firstName, String lastName, Float gpa, Set<Reader> readers) {
		super(firstName, lastName);
		this.gpa = gpa;
		this.readers = readers;
	}
	
	// GETTERS AND SETTERS
	
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    
    public Float getGpa() {return gpa;}
    public void setGpa(Float gpa) {this.gpa = gpa;}
    
    public Set<Reader> getReaders() {return readers;}
    public void setReaders(Set<Reader> readers) {this.readers = readers;}
}
