package com.sergiu.builders;

import com.sergiu.entity.SupervisorEntity;

import java.util.List;

public class SupervisorBuilder {
    public static SupervisorEntity build(List<String> fields) {
        SupervisorEntity supervisorEntity = new SupervisorEntity();
        supervisorEntity.setFirstName(String.valueOf(fields.get(0)));
        supervisorEntity.setLastName(String.valueOf(fields.get(1)));
        return supervisorEntity;
    }
}
