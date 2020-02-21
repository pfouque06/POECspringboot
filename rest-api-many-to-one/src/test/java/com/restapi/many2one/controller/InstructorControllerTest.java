package com.restapi.many2one.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.restapi.many2one.dao.InstructorRepository;
import com.restapi.many2one.entities.Instructor;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class InstructorControllerTest {

	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testGetInstructors() {
		Instructor t1 = new Instructor("t1", "t1", "t1");
		Instructor t2 = new Instructor("t2", "t2", "t2");
		entityManager.persist(t1);
		entityManager.persist(t2);
		
		List<Instructor> tableDB = instructorRepository.findAll();
		List<Instructor> table = new ArrayList<>();
		
		for (Instructor t : tableDB) {
			table.add(t);
		}
		assertThat(table.size()).isEqualTo(2);
		//fail("Not yet implemented");
	}

	@Test
	void testGetInstructorById() {
		Instructor t1 = new Instructor("t1", "t1", "t1");
		Instructor tDB = entityManager.persist(t1);
		Instructor t = instructorRepository.getOne(tDB.getId());
		assertEquals(tDB, t);
		assertThat(t.equals(tDB));
		//fail("Not yet implemented");
	}

	@Test
	void testCreateUser() {
		Instructor t1 = new Instructor("t1", "t1", "t1");
		Instructor tDB = entityManager.persist(t1);
		Instructor t = instructorRepository.getOne(tDB.getId());
		assertEquals(tDB, t);
		assertThat(t.equals(tDB));
		//fail("Not yet implemented");
	}

	@Test
	void testUpdateUser() {
		Instructor t1 = new Instructor("t1", "t1", "t1");
		entityManager.persist(t1);
		Instructor tDB = instructorRepository.getOne(t1.getId());
		tDB.setFirstName("USERO");
		entityManager.persist(tDB);
		assertThat(tDB.getFirstName()).isEqualTo("USERO");
		//fail("Not yet implemented");
	}

	@Test
	void testDeleteUser() {
		Instructor t1 = new Instructor("t1", "t1", "t1");
		Instructor t2 = new Instructor("t2", "t2", "t2");
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
