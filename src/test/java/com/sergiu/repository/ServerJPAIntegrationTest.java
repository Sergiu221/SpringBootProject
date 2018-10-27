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
import com.sergiu.entity.TeacherEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServerJPAIntegrationTest {
	@Autowired
	private TeacherRepository teacherRepository;

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
}
