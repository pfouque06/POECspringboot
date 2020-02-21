package com.restapi.many2many.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.many2many.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
}