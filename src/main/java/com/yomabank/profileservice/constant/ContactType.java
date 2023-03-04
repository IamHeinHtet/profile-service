package com.yomabank.profileservice.constant;

public enum ContactType {
    PHONE("PHONE"),
    EMAIL("EMAIL");
    private String value;

    ContactType(String value) {
        this.value = value;
    }
}
