package com.sergiu.repository;

import com.sergiu.entity.SupervisorEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@SpringBootTest(classes = SupervisorRepository.class)
public class SupervisorsRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Before
    public void init() {
        SupervisorEntity entity = new SupervisorEntity();

        entity.setFirstName("Supervisor Name1");
        entity.setLastName("Supervisor LastName1");
        entityManager.persist(entity);

        SupervisorEntity entity2 = new SupervisorEntity();

        entity.setFirstName("Supervisor Name2");
        entity.setLastName("Supervisor LastName2");
        entityManager.persist(entity2);
        entityManager.flush();
    }

    @After
    public void clear() {
        entityManager.clear();
        entityManager.flush();
    }

    @Test
    public void testFindByIdOnHallsRepository() {
        SupervisorEntity found = supervisorRepository.findById(1).get();
        assertEquals(found.getId(), 1);
    }

    @Test
    public void testDeleteByIdOnHallsRepository() {
        int totalNrOfHalls = supervisorRepository.findAll().size();
        supervisorRepository.deleteById(3);
        assertEquals(totalNrOfHalls - 1, supervisorRepository.findAll().size());
    }
}
