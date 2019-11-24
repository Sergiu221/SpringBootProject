package com.sergiu.service;

import com.sergiu.entity.HallEntity;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.DistributionRepository;
import com.sergiu.repository.HallRepository;
import com.sergiu.service.DistributionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


public class DistributionServiceTest {

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private HallRepository hallRepository;

    @Mock
    private DistributionRepository distributionRepository;

    @InjectMocks
    private DistributionService distributionService;

    private List<HallEntity> halls = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(candidateRepository.count()).thenReturn(15L);
        when(hallRepository.findAll()).thenReturn(retrieveHalls());
    }

    @Test
    public void testIsSufficientSeatsForExam() {
        Assert.assertTrue(distributionService.isSufficientSeatsForExam());
    }

    private List<HallEntity> retrieveHalls() {
        List<HallEntity> halls = new ArrayList<>();
        halls.add(new HallEntity("C201", 6, 10));
        halls.add(new HallEntity("C202", 5, 10));
        halls.add(new HallEntity("C203", 4, 10));
        return halls;
    }
}
