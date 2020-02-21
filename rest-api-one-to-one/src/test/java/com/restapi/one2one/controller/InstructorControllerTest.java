package com.restapi.one2one.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.restapi.one2one.dao.*;
import com.restapi.one2one.entities.Instructor;
import com.restapi.one2one.entities.InstructorDetail;

@RunWith(SpringRunner.class)
@DataJpaTest
//@ActiveProfiles("test")
class InstructorControllerTest {

	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void testGetInstructors() {
		InstructorDetail to1 = new InstructorDetail("yt", "hob");
		InstructorDetail to2 = new InstructorDetail("yt", "hob");
		Instructor t1 = new Instructor("t1", "t1", null, to1);
		Instructor t2 = new Instructor("t2", "t2", null, to2);
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
		InstructorDetail to1 = new InstructorDetail("yt", "hob");
		Instructor t1 = new Instructor("t1", "t1", null, to1);
		Instructor tDB = entityManager.persist(t1);
		Instructor t = instructorRepository.getOne(tDB.getId());
		assertEquals(tDB, t);
		assertThat(t.equals(tDB));
		//fail("Not yet implemented");
	}

	@Test
	void testCreateUser() {
		InstructorDetail to1 = new InstructorDetail("yt", "hob");
		Instructor t1 = new Instructor("t1", "t1", null, to1);
		Instructor tDB = entityManager.persist(t1);
		Instructor t = instructorRepository.getOne(tDB.getId());
		assertEquals(tDB, t);
		assertThat(t.equals(tDB));
		//fail("Not yet implemented");
	}

	@Test
	void testUpdateUser() {
		InstructorDetail to1 = new InstructorDetail("yt", "hob");
		Instructor t1 = new Instructor("t1", "t1", null, to1);
		entityManager.persist(t1);
		Instructor tDB = instructorRepository.getOne(t1.getId());
		tDB.setFirstName("USERO");
		entityManager.persist(tDB);
		assertThat(tDB.getFirstName()).isEqualTo("USERO");
		//fail("Not yet implemented");
	}

	@Test
	void testDeleteUser() {
		InstructorDetail to1 = new InstructorDetail("yt", "hob");
		InstructorDetail to2 = new InstructorDetail("yt", "hob");
		Instructor t1 = new Instructor("t1", "t1", null, to1);
		Instructor t2 = new Instructor("t2", "t2", null, to2);
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
