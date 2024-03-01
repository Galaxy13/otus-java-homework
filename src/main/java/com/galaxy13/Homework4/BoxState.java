package com.galaxy13.Homework4;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoxState {
    CLOSED("Closed"),
    OPENED("Opened");

    private final String value;
}
