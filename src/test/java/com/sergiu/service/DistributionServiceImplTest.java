package com.sergiu.service;

import com.sergiu.entity.HallEntity;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.DistributionRepository;
import com.sergiu.repository.HallRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


public class DistributionServiceImplTest {

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private HallRepository hallRepository;

    @Mock
    private DistributionRepository distributionRepository;

    @InjectMocks
    private DistributionServiceImpl distributionServiceImpl;

    private List<HallEntity> halls = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(candidateRepository.count()).thenReturn(15L);
        when(hallRepository.findAll()).thenReturn(retrieveHalls());
    }

    @Test
    public void testIsSufficientSeatsForExam() {
        Assert.assertTrue(distributionServiceImpl.isSufficientSeatsForExam());
    }

    private List<HallEntity> retrieveHalls() {
        List<HallEntity> halls = new ArrayList<>();
        halls.add(new HallEntity("C201", 6, 10));
        halls.add(new HallEntity("C202", 5, 10));
        halls.add(new HallEntity("C203", 4, 10));
        return halls;
    }
}
