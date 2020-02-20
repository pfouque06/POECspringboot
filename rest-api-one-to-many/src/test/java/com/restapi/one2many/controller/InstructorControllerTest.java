package com.restapi.one2many.controller;

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
import org.springframework.test.context.junit4.SpringRunner;

//import com.restapi.one2many.dao.CourseRepository;
import com.restapi.one2many.dao.InstructorRepository;
import com.restapi.one2many.entities.Course;
import com.restapi.one2many.entities.Instructor;


@RunWith(SpringRunner.class)
@DataJpaTest
class InstructorControllerTest {

	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testGetInstructors() {
		Course co1 = new Course("co1");
		Course co2 = new Course("co2");
		List<Course> lco1 = Arrays.asList(co1, co2);
		Course co3 = new Course("co3");
		Course co4 = new Course("co4");
		List<Course> lco2 = Arrays.asList(co3, co4);
		
		Instructor t1 = new Instructor("t1", "t1", "t1", lco1);
		Instructor t2 = new Instructor("t2", "t2", "t2", lco2);
		entityManager.persist(t1);
		entityManager.persist(t2);
		
		List<Instructor> tableDB = instructorRepository.findAll();
		List<Instructor> table = new ArrayList<>();
		
		for (Instructor t : tableDB) {
			table.add(t);
		}
		assertThat(table.size()).isEqualTo(2);
		//fail("Not yet implemented");;
	}

	@Test
	void testGetInstructorById() {
		Course co1 = new Course("co1");
		Course co2 = new Course("co2");
		List<Course> lco1 = Arrays.asList(co1, co2);
		Instructor t1 = new Instructor("t1", "t1", "t1", lco1);
		Instructor tDB = entityManager.persist(t1);
		Instructor t = instructorRepository.getOne(tDB.getId());
		assertEquals(tDB, t);
		assertThat(t.equals(tDB));
		//fail("Not yet implemented");
	}

	@Test
	void testCreateUser() {
		Course co1 = new Course("co1");
		Course co2 = new Course("co2");
		List<Course> lco1 = Arrays.asList(co1, co2);
		Instructor t1 = new Instructor("t1", "t1", "t1", lco1);
		Instructor tDB = entityManager.persist(t1);
		Instructor t = instructorRepository.getOne(tDB.getId());
		assertEquals(tDB, t);
		assertThat(t.equals(tDB));
		//fail("Not yet implemented");
	}

	@Test
	void testUpdateUser() {
		Course co1 = new Course("co1");
		Course co2 = new Course("co2");
		List<Course> lco1 = Arrays.asList(co1, co2);
		Instructor t1 = new Instructor("t1", "t1", "t1", lco1);
		entityManager.persist(t1);
		Instructor tDB = instructorRepository.getOne(t1.getId());
		tDB.setFirstName("USERO");
		entityManager.persist(tDB);
		assertThat(tDB.getFirstName()).isEqualTo("USERO");
		//fail("Not yet implemented");
	}

	@Test
	void testDeleteUser() {
		Course co1 = new Course("co1");
		Course co2 = new Course("co2");
		List<Course> lco1 = Arrays.asList(co1, co2);
		Course co3 = new Course("co3");
		Course co4 = new Course("co4");
		List<Course> lco2 = Arrays.asList(co3, co4);
		
		Instructor t1 = new Instructor("t1", "t1", "t1", lco1);
		Instructor t2 = new Instructor("t2", "t2", "t2", lco2);
		Instructor tDB = entityManager.persist(t1);
		entityManager.persist(t2);
		entityManager.remove(tDB);
		
		List<Instructor> tableDB = instructorRepository.findAll();
		List<Instructor> table = new ArrayList<>();
		
		for (Instructor t : tableDB) {
			table.add(t);
		}
		assertThat(table.size()).isEqualTo(1);
		//fail("Not yet implemented");
	}

}
