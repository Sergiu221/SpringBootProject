package com.sergiu.service;

import com.sergiu.dto.GradesDTO;
import com.sergiu.repository.GradeRepository;
import com.sergiu.transformer.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradesServiceImpl implements GradesService {

    private GradeRepository gradeRepository;
    private Transformer transformer;

    @Autowired
    public void setGradeRepository(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Autowired
    public void setTransformer(Transformer transformer) {
        this.transformer = transformer;
    }

    @Override
    public List<GradesDTO> getAllGrades() {
        return (List<GradesDTO>) transformer.map(gradeRepository.findAll(), GradesDTO.class);
    }

    @Override
    public void add(GradesDTO gradesDTO) {

    }

    @Override
    public GradesDTO updateGrades(Long cnp, GradesDTO gradesDTO) {
        return null;
    }

    @Override
    public void deleteGrades(Long cnp) {

    }
}
