package com.sergiu.builders;

import com.sergiu.entity.Grades;

import java.util.List;

public class GradesBuilder {
    public static Grades build(List<String> fields) {
        Grades grades = new Grades();
        grades.setCnp(Long.valueOf(fields.get(0)));
        grades.setFirstGrade(Double.valueOf(fields.get(1)));
        grades.setNameProfessorOne(fields.get(2));
        grades.setSecondGrade(Double.valueOf(fields.get(3)));
        grades.setNameProfessorTwo(fields.get(4));
        return grades;
    }
}
