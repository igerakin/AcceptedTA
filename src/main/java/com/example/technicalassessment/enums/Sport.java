package com.example.technicalassessment.enums;

public enum Sport {
    FOOTBALL(1, "Football"),
    BASKETBALL(2, "Basketball");
    
    private final int code;
    private final String name;
    
    Sport(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public static Sport fromCode(int code) {
        for (Sport sport : Sport.values()) {
            if (sport.code == code) {
                return sport;
            }
        }
        throw new IllegalArgumentException("Invalid sport code: " + code);
    }
}