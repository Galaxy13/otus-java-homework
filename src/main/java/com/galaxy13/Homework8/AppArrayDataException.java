package com.galaxy13.Homework8;

public class AppArrayDataException extends Exception {
    public AppArrayDataException(int row, int col) {
        super(String.format("Non-integer in row %s, column %s", row + 1, col + 1));
    }
}
