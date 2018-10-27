package com.sergiu.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sergiu.Application;
import com.sergiu.entity.HallEntity;
import com.sergiu.entity.TeacherEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServerJPAIntegrationTest {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private HallRepository hallRepository;

	@Test
	public void givenTeacherEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
		TeacherEntity entity = new TeacherEntity();
		entity.setId(1);
		entity.setFirstName("firtsName");
		entity.setMiddleName("middleName");
		entity.setLastName("lastName");
		TeacherEntity expected = teacherRepository.save(entity);
		Optional<TeacherEntity> foundEntity = teacherRepository.findById(expected.getId());
		assertNotNull(foundEntity);
		assertEquals(expected, foundEntity.get());
		teacherRepository.delete(foundEntity.get());
	}
	
	@Test
	public void givenHallEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
		HallEntity entity = new HallEntity();
		entity.setId(2);
		entity.setName("C308");
		entity.setSize(50);
		HallEntity expected = hallRepository.save(entity);
		Optional<HallEntity> foundEntity = hallRepository.findById(expected.getId());
		assertNotNull(foundEntity);
		assertEquals(expected, foundEntity.get());
		hallRepository.delete(foundEntity.get());
	}
}
