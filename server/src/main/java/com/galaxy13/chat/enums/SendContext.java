package com.galaxy13.chat.enums;

public enum SendContext {
    ALL("All"),
    PERSONAL("Prsn"),
    INFO("Info");

    private final String value;

    SendContext(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
