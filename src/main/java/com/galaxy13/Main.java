package com.galaxy13;

import com.galaxy13.Homework8.AppArrayDataException;
import com.galaxy13.Homework8.AppArraySizeExtension;
import com.galaxy13.Homework8.Homework8;

public class Main {

    private static void handleSumOf2DArray(String[][] strArray) {
        try {
            System.out.println(Homework8.sumOF2DArray(strArray));
        } catch (AppArraySizeExtension | AppArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String[][] testArray1 = {{}};
        handleSumOf2DArray(testArray1);

        String[][] testArray2 = {{"1", "2", "5", "10"}, {"23", "45"}, {"45", "23", "90", "0"}, {"10", "13", "67", "10"}};
        handleSumOf2DArray(testArray2);

        String[][] testArray3 = {{"1", "2", "5", "10"}, {"23", "45", "32", "90"}, {"45", "23", "notANumber", "0"}, {"10", "13", "67", "10"}};
        handleSumOf2DArray(testArray3);

        String[][] testArray4 = {{"1", "2", "5", "10"}, {"23", "45", "32", "90"}, {"45", "23", "0", "0"}, {"10", "13", "67", "10"}};
        handleSumOf2DArray(testArray4);
    }
}