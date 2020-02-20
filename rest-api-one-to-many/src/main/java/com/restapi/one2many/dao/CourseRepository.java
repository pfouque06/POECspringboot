package com.restapi.one2many.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;



@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	List<Course> findByInstructorId(Long instructorId);

	Optional<Course> findByIdAndInstructorId(Long id, Long instructorId);
}
