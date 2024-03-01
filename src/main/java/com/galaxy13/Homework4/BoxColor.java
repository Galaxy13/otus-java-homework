package com.galaxy13.Homework4;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoxColor {
    RED("Red"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    WHITE("White"),
    BLACK("Black"),
    GREEN("Green");

    private final String color;
}
