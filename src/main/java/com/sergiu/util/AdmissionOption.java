package com.sergiu.util;

public enum AdmissionOption {

    RO_BUGET("RO_BUGET"),
    RO_TAXA("RO_TAXA"),
    EN_BUGET("EN_BUGET"),
    EN_TAXA("EN_TAXA"),
    MD_RO_BUGET("MD_RO_BUGET"),
    MD_EN_BUGET("MD_EN_BUGET");

    private String type;

    AdmissionOption(String type) {
        this.type = type;
    }

    public static AdmissionOption get(String value) {
        if ("RO_BUGET".equals(value)) {
            return RO_BUGET;
        }

        if ("RO_TAXA".equals(value)) {
            return RO_TAXA;
        }

        if ("EN_BUGET".equals(value)) {
            return EN_BUGET;
        }

        if ("EN_TAXA".equals(value)) {
            return EN_TAXA;
        }

        if ("MD_RO_BUGET".equals(value)) {
            return MD_RO_BUGET;
        }

        if ("MD_EN_BUGET".equals(value)) {
            return MD_EN_BUGET;
        }
        return null;
    }
}
