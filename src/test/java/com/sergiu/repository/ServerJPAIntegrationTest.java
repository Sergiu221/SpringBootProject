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
import com.sergiu.entity.CandidateEntity;
import com.sergiu.entity.HallEntity;
import com.sergiu.entity.SupervisorEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServerJPAIntegrationTest {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void givenSupervisorEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
        SupervisorEntity entity = new SupervisorEntity();
        entity.setId(1);
        entity.setFirstName("firtsName");
        entity.setMiddleName("middleName");
        entity.setLastName("lastName");
        SupervisorEntity expected = supervisorRepository.save(entity);
        Optional<SupervisorEntity> foundEntity = supervisorRepository.findById(expected.getId());
        assertNotNull(foundEntity);
        assertEquals(expected, foundEntity.get());
        supervisorRepository.delete(foundEntity.get());
    }
}
