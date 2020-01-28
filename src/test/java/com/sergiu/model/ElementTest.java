package com.sergiu.model;

import com.sergiu.entity.Candidate;
import com.sergiu.entity.CategoryEntity;
import com.sergiu.entity.HallEntity;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

import java.math.BigDecimal;

public class ElementTest {

    private HallEntity hallEntity;

    private CategoryEntity categoryEntity;
    private CategoryEntity categoryEntity2;

    @Before
    public void init() {
        hallEntity = new HallEntity("Sala1", 12, 7);
        Candidate entity = new Candidate();
        entity.setCnp(1940122374500L);
        entity.setFirstName("Sergiu-Adrian");
        entity.setLastName("Volocaru");
        entity.setHighSchool("Liceul Teoretic Emil Racovita");

        Candidate entity2 = new Candidate();
        entity2.setCnp(1940122374502L);
        entity2.setFirstName("Sergiu-Constatin");
        entity2.setLastName("Volocaru");
        entity2.setHighSchool("Liceul Teoretic Emil Racovita");
        categoryEntity = new CategoryEntity();
        categoryEntity2 = new CategoryEntity();
        categoryEntity.getCandidateEntities().add(entity);
        categoryEntity.getCandidateEntities().add(entity2);
        categoryEntity.getCandidateEntities().add(entity2);
        categoryEntity.getCandidateEntities().add(entity);
        categoryEntity2.getCandidateEntities().add(entity2);
        categoryEntity2.getCandidateEntities().add(entity2);
    }

    @Test
    public void testCohesion() {
        Element element = new Element(categoryEntity, hallEntity);
        Assert.assertEquals(new BigDecimal("0.57"), element.getCohesion());
        Assert.assertEquals(4, element.restOfCandidates());
    }

    @Test
    public void testCompareTo() {

        Element element = new Element(categoryEntity, hallEntity);
        Element element1 = new Element(categoryEntity2, hallEntity);
        Assert.assertEquals(1, element.compareTo(element1));
    }

    @Test
    public void testCohesionMax() {
        hallEntity = new HallEntity("Sala1", 8, 4);
        Element element = new Element(categoryEntity, hallEntity);
        Assert.assertEquals(BigDecimal.ONE, element.getCohesion());
    }

}
