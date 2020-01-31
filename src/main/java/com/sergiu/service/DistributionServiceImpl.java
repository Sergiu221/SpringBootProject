package com.sergiu.service;

import com.sergiu.entity.*;
import com.sergiu.model.Element;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.HallRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergiu.repository.DistributionRepository;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DistributionServiceImpl implements DistributionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionServiceImpl.class);

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DistributionRepository distributionRepository;


    @Override
    public void clear() {
        distributionRepository.deleteAllInBatch();
    }

    @Override
    public boolean isSufficientSeatsForExam() {
        long numberOfCandidates = candidateRepository.count();
        long numberOfSeatsFromHalls = hallRepository.findAll().stream().mapToLong(Hall::getUtilizableSize).sum();
        return numberOfSeatsFromHalls >= numberOfCandidates;
    }

    @Override
    public void distributeCandidatesIntoHalls() {

        SortedSet<Element> setOfCategoriesWithHalls = fillSet();

        while (!setOfCategoriesWithHalls.isEmpty()) {

            displayContent(setOfCategoriesWithHalls);

            LOGGER.info("====> SIZE OF SUBSETS IS :" + setOfCategoriesWithHalls.size() + "<=======");
            Element element = setOfCategoriesWithHalls.last();
            setOfCategoriesWithHalls.remove(element);

            if (!element.getCohesion().equals(BigDecimal.ZERO)) {

                List<Candidate> listMove;
                if (element.restOfCandidates() == 0) {
                    listMove = element.getCategory().getCandidateEntities().subList(0, element.getHall().getUtilizableSize());
                } else {
                    listMove = element.getCategory().getCandidateEntities().subList(0, element.restOfCandidates());
                }
                int size = listMove.size();
                element.getHall().getListCandidates().addAll(listMove);
                List<Candidate> remainingList = getRemainingCandidates(element, size);
                element.getCategory().setCandidateEntities(remainingList);

                insertCandidatesIntoHall(listMove, element.getHall());
                LOGGER.info("Insert [{}] candidates from category [{}] into hall [{}]! ", size, element.getCategory().getId(), element.getHall().getId());
            }

            setOfCategoriesWithHalls = refreshElements(setOfCategoriesWithHalls);
        }
    }

    private void displayContent(SortedSet<Element> setOfCategoriesWithHalls) {
        for (Element element : setOfCategoriesWithHalls) {
            LOGGER.info(element.toString());
        }
    }

    private List<Candidate> getRemainingCandidates(Element element, int size) {
        if (size == element.getCategory().getCandidateEntities().size()) {
            return new ArrayList<>();
        }
        return element.getCategory().getCandidateEntities().subList(size, element.getCategory().getCandidateEntities().size());
    }

    private SortedSet<Element> refreshElements(SortedSet<Element> elementSortedSet) {
        SortedSet<Element> result = new TreeSet<>();
        for (Element element : elementSortedSet) result.add(element);
        return result;
    }

    @Override
    public SortedSet<Element> fillSet() {
        List<Category> categories = categoryService.getAllCategoriesWithCandidates();
        List<Hall> halls = hallRepository.findAll();

        SortedSet<Element> result = new TreeSet<>();
        for (Category category : categories) {
            for (Hall hall : halls) {
                result.add(new Element(category, hall));
            }
        }
        return result;
    }

    private void insertCandidatesIntoHall(List<Candidate> candidateEntities, Hall hall) {
        List<DistributionEntity> distributions = new ArrayList<>();
        for (Candidate candidate : candidateEntities) {
            DistributionEntity distribution = new DistributionEntity(new DistributionId(candidate.getCnp(), hall.getId()));
            distributions.add(distribution);
        }
        filterNullValue(distributions);
        if (!distributions.isEmpty()) {
            for (DistributionEntity distributionEntity : distributions) {
                distributionRepository.flush();
                distributionRepository.saveAndFlush(distributionEntity);
            }
        }
    }

    private void filterNullValue(List<DistributionEntity> distributionEntities) {
        distributionEntities.removeIf(d -> null == d.getDistributionId());
    }
}