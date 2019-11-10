package com.sergiu.controller;

import java.util.List;

import javax.validation.Valid;

import com.sergiu.service.HallsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sergiu.entity.HallEntity;
import com.sergiu.exception.ResourceNotFoundException;
import com.sergiu.dto.HallDTO;

@CrossOrigin
@RestController
public class HallsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HallsController.class);

    @Autowired
    private HallsService hallsService;

    @GetMapping("/halls")
    public List<HallDTO> getAllHalls() {
        return hallsService.getAllHalls();
    }

    @PostMapping("/halls")
    public void createHall(@Valid @RequestBody HallDTO hallDTO) {
        hallsService.createHall(hallDTO);
    }

    @GetMapping("/halls/{id}")
    public HallDTO getHallById(@PathVariable(value = "id") Integer id) {
        return hallsService.getHallById(id);
    }

    @PutMapping("/halls/{id}")
    public HallDTO updateHall(@PathVariable(value = "id") Integer id, @Valid @RequestBody HallDTO hallDTO) {
        return hallsService.updateHall(id, hallDTO);
    }

    @DeleteMapping("/halls/{id}")
    public ResponseEntity<?> deleteHall(@PathVariable(value = "id") Integer id) {
        hallsService.deleteHall(id);
        return ResponseEntity.ok().build();
    }
}
