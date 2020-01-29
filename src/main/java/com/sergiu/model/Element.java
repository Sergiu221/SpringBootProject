package com.sergiu.model;

import com.sergiu.entity.Category;
import com.sergiu.entity.HallEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Element implements Comparable {
    private Category category;

    private HallEntity hallEntity;

    public Element(Category category, HallEntity hallEntity) {
        this.category = category;
        this.hallEntity = hallEntity;
    }

    public BigDecimal getCohesion() {

        if (hallEntity.getUtilizableSize() == 0) {
            return BigDecimal.ZERO;
        }

        if (category.getCandidateEntities().size() == 0) {
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
        int nrOfCandidates = category.getCandidateEntities().size();
        int nrOfAvailableSpaces = hallEntity.getUtilizableSize();

        return nrOfCandidates % nrOfAvailableSpaces;
    }

    @Override
    public int compareTo(Object o) {
        Element object = (Element) o;
        int resultCohesion = this.getCohesion().compareTo(object.getCohesion());
        if (resultCohesion != 0)
            return resultCohesion;
        int resultCandidatesComparison = this.category.getCandidateEntities().size() - object.getCategory().getCandidateEntities().size();

        if (resultCandidatesComparison != 0) {
            return resultCandidatesComparison;
        }

        int resultHallSpots = this.hallEntity.getUtilizableSize() - object.getHallEntity().getUtilizableSize();
        if (resultHallSpots != 0) {
            return resultHallSpots;
        }

        int resultNameCategory = this.category.getName().compareTo(object.getCategory().getName());
        if (resultNameCategory != 0) {
            return resultNameCategory;
        }

        int resultNameHall = this.hallEntity.getName().compareTo(object.getHallEntity().getName());
        if (resultNameHall != 0) {
            return resultNameHall;
        }


        return 0;
    }

    @Override
    public String toString() {
        return "Cohesion=" + getCohesion() + " category_id=" + category.getId() + " with hall_id" + hallEntity.getId();
    }

    public Category getCategory() {
        return category;
    }

    public HallEntity getHallEntity() {
        return hallEntity;
    }

    @Override
    public boolean equals(Object o) {
        Element object = (Element) o;

        return (this.getCategory().getId() == object.getCategory().getId()
                && this.getHallEntity().getId() == object.getHallEntity().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, hallEntity);
    }
}
