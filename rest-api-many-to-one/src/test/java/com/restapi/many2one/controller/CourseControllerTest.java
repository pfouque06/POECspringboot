package com.restapi.many2one.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.restapi.many2one.dao.CourseRepository;
import com.restapi.many2one.dao.InstructorRepository;
import com.restapi.many2one.entities.Course;
import com.restapi.many2one.entities.Instructor;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class CourseControllerTest {
	
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private TestEntityManager entityManager;

	@Test
	void testGetCoursesByInstructor() {
		Instructor t1 = new Instructor("t1", "t1", "t1");
		entityManager.persist(t1);
		
		Course co1 = new Course("co1");
		co1.setInstructor(t1);
		//courseRepository.save(co1);
		entityManager.persist(co1);
		
		Course co2 = new Course("co2");
		co2.setInstructor(t1);
		//courseRepository.save(co2);
		entityManager.persist(co2);
		
		Instructor tDB = instructorRepository.getOne(t1.getId());
		List<Course> tableDB = courseRepository.findByInstructorId(tDB.getId());
		List<Course> table = new ArrayList<>();
		
		for (Course t : tableDB) {
			table.add(t);
		}
		assertThat(table.size()).isEqualTo(2);
		//fail("Not yet implemented");;
	}

	@Test
	void testSave() {
		Instructor t1 = new Instructor("t1", "t1", "t1");
		entityManager.persist(t1);

		Course co1 = new Course("co1");
		co1.setInstructor(t1);
		//courseRepository.save(co1);
		entityManager.persist(co1);
		
		Instructor tDB = instructorRepository.getOne(t1.getId());
		List<Course> tableDB = courseRepository.findByInstructorId(tDB.getId());
		List<Course> table = Arrays.asList(co1);
		assertEquals(tableDB, table);
		assertThat(table.equals(tableDB));
		//fail("Not yet implemented");
	}

	@Test
	void testUpdateCourse() {
		Instructor t1 = new Instructor("t1", "t1", "t1");
		entityManager.persist(t1);

		Course co1 = new Course("co1");
		co1.setInstructor(t1);
		//courseRepository.save(co1);
		entityManager.persist(co1);

		Instructor tDB = instructorRepository.getOne(t1.getId());
		List<Course> tableDB = courseRepository.findByInstructorId(tDB.getId());
		assertThat(tableDB.size()).isEqualTo(1);
		Course co2 = tableDB.get(0);
		co2.setTitle("co2");
		entityManager.persist(co2);
		assertThat(co2.getTitle()).isEqualTo("co2");
		//fail("Not yet implemented");
	}

	@Test
	void testDeleteCourse() {
		Instructor t3 = new Instructor("t3", "t3", "t3");
		Instructor tDB = entityManager.persist(t3);

		//Course co1 = new Course("co1", t3);
		Course co1 = new Course("co1");
		co1.setInstructor(t3);
		Course cDB = entityManager.persist(co1);

		Course co2 = new Course("co2", t3);
		//Course co2 = new Course("co2");
		//co2.setInstructor(t3);
		entityManager.persist(co2);

		entityManager.remove(cDB);  

		//Instructor tDB = instructorRepository.getOne(t3.getId());
		List<Course> tableDB = courseRepository.findByInstructorId(tDB.getId());
		List<Course> table = new ArrayList<>();
		
		for (Course t : tableDB) {
			table.add(t);
		}
		assertThat(table.size()).isEqualTo(1);
		//fail("Not yet implemented");
	}

}
