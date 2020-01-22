package com.sergiu.util;

public enum AdmissionType {

    ADMITERE("Admitere"), PREADMITERE("PreAdmitere"), OLIMPIC("Olimpic");
    private String type;

    AdmissionType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
