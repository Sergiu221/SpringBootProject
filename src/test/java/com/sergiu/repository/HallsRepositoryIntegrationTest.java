package com.sergiu.repository;

import com.sergiu.entity.HallEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class HallsRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HallRepository hallRepository;

    @Before
    public void init() {
        HallEntity entity = new HallEntity();
        entity.setName("Sala1");
        entity.setSize(60);
        entity.setUtilizableSize(30);

        entityManager.persist(entity);

        HallEntity entity2 = new HallEntity();
        entity2.setName("Sala2");
        entity2.setSize(52);
        entity2.setUtilizableSize(25);

        entityManager.persist(entity2);
        entityManager.flush();
    }

    @Test
    public void testFindByIdOnHallsRepository() {
        HallEntity found = hallRepository.findById(1).get();
        assertEquals(found.getId(), 1);
    }

    @Test
    public void testDeleteByIdOnHallsRepository() {
        int totalNrOfHalls = hallRepository.findAll().size();
        hallRepository.deleteById(3);
        assertEquals(totalNrOfHalls - 1, hallRepository.findAll().size());
    }
}
