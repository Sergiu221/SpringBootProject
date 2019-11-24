package com.sergiu.model;

import com.sergiu.entity.CategoryEntity;
import com.sergiu.entity.HallEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Element implements Comparable {
    private CategoryEntity categoryEntity;

    private HallEntity hallEntity;

    public Element(CategoryEntity categoryEntity, HallEntity hallEntity) {
        this.categoryEntity = categoryEntity;
        this.hallEntity = hallEntity;
    }

    public BigDecimal getCohesion() {

        if (hallEntity.getUtilizableSize() == 0) {
            return BigDecimal.ZERO;
        }

        if (categoryEntity.getCandidateEntities().size() == 0) {
            return BigDecimal.ZERO;
        }

        if (restOfCandidates() == 0) {
            return BigDecimal.ONE;
        }

        BigDecimal restCandidates = new BigDecimal(restOfCandidates());
        BigDecimal availableSpaces = new BigDecimal(this.hallEntity.getUtilizableSize());
        return restCandidates.divide(availableSpaces, 2, RoundingMode.FLOOR);
    }

    public int restOfCandidates() {
        int nrOfCandidates = categoryEntity.getCandidateEntities().size();
        int nrOfAvailableSpaces = hallEntity.getUtilizableSize();

        return nrOfCandidates % nrOfAvailableSpaces;
    }

    @Override
    public int compareTo(Object o) {
        Element object = (Element) o;
        int resultCohesion = this.getCohesion().compareTo(object.getCohesion());
        if (resultCohesion != 0)
            return resultCohesion;
        int resultCandidatesComparison = this.categoryEntity.getCandidateEntities().size() - object.getCategoryEntity().getCandidateEntities().size();

        if (resultCandidatesComparison != 0) {
            return resultCandidatesComparison;
        }

        int resultHallSpots = this.hallEntity.getUtilizableSize() - object.getHallEntity().getUtilizableSize();
        if (resultHallSpots != 0) {
            return resultHallSpots;
        }

        int resultNameCategory = this.categoryEntity.getName().compareTo(object.getCategoryEntity().getName());
        if (resultNameCategory != 0) {
            return resultHallSpots;
        }

        int resultNameHall = this.hallEntity.getName().compareTo(object.getHallEntity().getName());
        if (resultNameHall != 0) {
            return resultNameHall;
        }


        return 0;
    }

    @Override
    public String toString() {
        return "Cohesion=" + getCohesion() + " category_id=" + categoryEntity.getId() + " with hall_id" + hallEntity.getId();
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public HallEntity getHallEntity() {
        return hallEntity;
    }

    @Override
    public boolean equals(Object o) {
        Element object = (Element) o;

        return (this.getCategoryEntity().getId() == object.getCategoryEntity().getId()
                && this.getHallEntity().getId() == object.getHallEntity().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryEntity, hallEntity);
    }
}
