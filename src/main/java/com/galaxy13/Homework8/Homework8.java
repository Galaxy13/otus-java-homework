package com.galaxy13.Homework8;

public class Homework8 {
    public static int sumOF2DArray(String[][] intArray) throws AppArrayDataException, AppArraySizeExtension {
        if (intArray.length != 4) {
            throw new AppArraySizeExtension("Array size is not 4x4");
        }
        int arraySum = 0;
        for (int row = 0; row < intArray.length; row++) {
            if (intArray[row].length != 4) {
                throw new AppArraySizeExtension("Array size is not 4x4");
            }
            for (int col = 0; col < intArray[row].length; col++) {
                try {
                    arraySum += Integer.parseInt(intArray[row][col]);
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException(row, col);
                }
            }
        }
        return arraySum;
    }
}
