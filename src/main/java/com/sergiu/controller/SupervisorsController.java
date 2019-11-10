package com.sergiu.controller;

import java.util.List;

import javax.validation.Valid;

import com.sergiu.service.SupervisorService;
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

import com.sergiu.model.SupervisorDTO;

@CrossOrigin
@RestController
public class SupervisorsController {

    @Autowired
    private SupervisorService supervisorService;

    @GetMapping("/supervisors")
    public List<SupervisorDTO> getAllSupervisors() {
        return supervisorService.getAllSupervisors();
    }

    @PostMapping("/supervisors")
    public void createSupervisor(@Valid @RequestBody SupervisorDTO supervisorDTO) {
        supervisorService.createSupervisor(supervisorDTO);
    }

    @GetMapping("/supervisors/{id}")
    public SupervisorDTO getSupervisorById(@PathVariable(value = "id") Integer id) {
        return supervisorService.getSupervisorById(id);
    }

    @PutMapping("/supervisors/{id}")
    public SupervisorDTO updateSupervisor(@PathVariable(value = "id") Integer id, @Valid @RequestBody SupervisorDTO supervisorDTO) {
        return supervisorService.updateSupervisor(id, supervisorDTO);
    }

    @DeleteMapping("/supervisors/{id}")
    public ResponseEntity<?> deleteSupervisor(@PathVariable(value = "id") Integer id) {
        supervisorService.deleteSupervisor(id);
        return ResponseEntity.ok().build();
    }
}
