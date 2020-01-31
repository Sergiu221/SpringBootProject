package com.sergiu.service;

import com.sergiu.entity.Candidate;
import com.sergiu.entity.Category;
import com.sergiu.entity.Hall;
import com.sergiu.model.Element;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.DistributionRepository;
import com.sergiu.repository.HallRepository;
import com.sergiu.util.AdmissionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import static org.mockito.Mockito.when;


public class DistributionServiceImplTest {

    @Mock
    private CategoryServiceImpl categoryService;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private HallRepository hallRepository;

    @Mock
    private DistributionRepository distributionRepository;

    @InjectMocks
    private DistributionServiceImpl distributionServiceImpl;

    private List<Hall> halls = new ArrayList<>();

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

    private List<Hall> retrieveHalls() {
        List<Hall> halls = new ArrayList<>();
        halls.add(new Hall(1, "C201", 6, 10));
        halls.add(new Hall(2, "C202", 5, 10));
        halls.add(new Hall(3, "C203", 4, 10));
        return halls;
    }

    private List<Category> retrieveCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Category1", "", "", AdmissionType.ADMITERE));
        categories.add(new Category(2, "Category2", "", "", AdmissionType.ADMITERE));
        return categories;
    }

    private List<Candidate> retrieveCandidates() {
        Category category1 = new Category(1, "", "", "", AdmissionType.ADMITERE);
        Category category2 = new Category(2, "", "", "", AdmissionType.ADMITERE);
        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate(123L, "FirstName1", "", category1, ""));
        candidates.add(new Candidate(133L, "FirstName2", "", category2, ""));
        return candidates;
    }

    @Test
    public void testFillSet() {
        when(categoryService.getAllCategoriesWithCandidates()).thenReturn(retrieveCategories());
        SortedSet<Element> table = distributionServiceImpl.fillSet();
        Assert.assertEquals(6, table.size());
    }
}
