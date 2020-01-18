package com.sergiu.builders;

import com.sergiu.entity.HallEntity;

import java.util.List;

public class HallBuilder {

    public static HallEntity build(List<String> fields) {
        HallEntity hallEntity = new HallEntity();
        hallEntity.setName(String.valueOf(fields.get(0)));
        hallEntity.setSize(Integer.valueOf(fields.get(1)));
        hallEntity.setUtilizableSize(Integer.valueOf(fields.get(2)));
        return hallEntity;
    }
}
