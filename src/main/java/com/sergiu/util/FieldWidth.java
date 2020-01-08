package com.sergiu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldWidth {
    private static final Logger LOGGER = LoggerFactory.getLogger(FieldWidth.class);

    public static int getPredefinedWidth(String fieldName) {
        switch (fieldName) {
            case "cnp":
                return 80;
            case "firstName":
                return 50;
            case "lastName":
                return 100;
            case "highSchool":
                return 150;
            default:
                LOGGER.info("For filed:" + fieldName + "there is no width defined. Default will be 100");
                return 100;
        }
    }
}
