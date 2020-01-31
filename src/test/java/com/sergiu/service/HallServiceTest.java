package com.sergiu.service;

import com.sergiu.entity.Hall;
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

public class HallServiceTest {

    @InjectMocks
    private HallsServiceImpl hallsService;

    @Mock
    private HallRepository hallRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(hallRepository.findAll()).thenReturn(retrieveHalls());
    }


    @Test
    public void testSelectHalls() {
        List<Hall> halls = hallsService.selectHalls(110);

        int value = 0;
        for (Hall hall : halls) {
            value = value + hall.getUtilizableSize();
        }

        Assert.assertEquals(value, 120);
    }


    private List<Hall> retrieveHalls() {
        List<Hall> halls = new ArrayList<>();
        halls.add(new Hall(1, "C201", 150, 100));
        halls.add(new Hall(2, "C202", 100, 60));
        halls.add(new Hall(3, "C203", 100, 60));
        return halls;
    }
}
