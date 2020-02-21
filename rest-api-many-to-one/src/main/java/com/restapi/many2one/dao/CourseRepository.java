package com.restapi.many2one.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.restapi.many2one.entities.Course;



@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	List<Course> findByInstructorId(Long instructorId);

	Optional<Course> findByIdAndInstructorId(Long id, Long instructorId);
}
