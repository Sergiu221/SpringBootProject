package com.sergiu.service;

import com.sergiu.dto.GradesDTO;
import com.sergiu.entity.Grades;
import com.sergiu.repository.GradeRepository;
import com.sergiu.transformer.GradesTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradesServiceImpl implements GradesService {

    private GradeRepository gradeRepository;
    private GradesTransformer transformer;

    @Autowired
    public void setGradeRepository(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Autowired
    public void setTransformer(GradesTransformer transformer) {
        this.transformer = transformer;
    }

    @Override
    public List<GradesDTO> getAllGrades() {
        return transformer.toDTO(gradeRepository.findAll());
    }

    @Override
    public void add(GradesDTO gradesDTO) {
        Grades grades = transformer.toEntity(gradesDTO);
        gradeRepository.save(grades);
    }

    @Override
    public GradesDTO updateGrades(Long cnp, GradesDTO gradesDTO) {
        Grades grades = gradeRepository.save(transformer.toEntity(gradesDTO));
        return transformer.toDTO(grades);
    }

    @Override
    public void deleteGrades(Long cnp) {
        gradeRepository.deleteById(cnp);
    }
}
