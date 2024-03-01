package com.galaxy13.Homework3;

import java.util.Arrays;

interface CaseCheck{
    boolean caseCheck(int row, int col);
}
public class Homework3 {
    public static int sumOfPositiveElements(int[][] intArray){
        int arrSumBiggerZero = 0;
        for (int[] row: intArray){
            for (int number: row){
                if (number > 0){
                    arrSumBiggerZero += number;
                }
            }
        }
        return arrSumBiggerZero;

        //or
//        return Arrays.stream(intArray)
//                .flatMapToInt(Arrays::stream)
//                .filter(number -> number > 0)
//                .sum();
    }

    public static void starSquare(int squareSize){
        String squareRow = ("* ".repeat(squareSize) + "\n").repeat(squareSize);
        System.out.println(squareRow);

        //or solution without extra memory
//        for (int row=0; row < squareSize; row++){
//            for (int col=0; col < squareSize; col++){
//                System.out.print(" * ");
//            }
//            System.out.print("\n");
//        }
    }

    static void loopCase(int[][] intArrayPointer, CaseCheck caseCheckFunction){
        for (int row=0; row < intArrayPointer.length; row++){
            for (int col=0; col < intArrayPointer.length; col++){
                if (caseCheckFunction.caseCheck(row, col)){
                    intArrayPointer[row][col] = 0;
                }
            }
        }
    }

    static void checkEmptyArray(int[][] checkingArray) throws Exception {
        if (checkingArray.length == 0) {
            throw new Exception("Empty array");
        }
        if (Arrays.stream(checkingArray).allMatch(innerArray -> innerArray.length == 0)) {
            throw new Exception("All inner arrays are empty.");
        }
    }

    public static void diagonalsZeroing(int[][] intArray, DiagonalType type) throws Exception{
        checkEmptyArray(intArray);
        for (int[] innerArray: intArray){
            if (innerArray.length != intArray.length){
                throw new Exception("Array is not symmetric. Diagonal not defined");
            }
        }
        CaseCheck diagonalType;
        switch (type){
            case BOTH -> diagonalType = (row, col) -> (row == col || intArray.length - col - 1 == row);
            case MAIN_DIAGONAL -> diagonalType = (row, col) -> (row == col);
            case SECONDARY_DIAGONAL -> diagonalType = (row, col) -> intArray.length - col - 1 == row;

            default -> diagonalType = ((row, col) -> false);
        }
        loopCase(intArray, diagonalType);
    }

    public static int findMax(int[][] intArray) throws Exception{
        checkEmptyArray(intArray);
        //
        int max_element = Integer.MIN_VALUE;
        for (int[] innerIntArray : intArray) {
            for (int number : innerIntArray) {
                max_element = Integer.max(max_element, number);
            }
        }
        return max_element;
        //

//        or
//        OptionalInt optionalInt = Arrays.stream(intArray)
//                .flatMapToInt(Arrays::stream)
//                .max()
//                .getAsInt();
//        }
    }

    public static int sumOfSecondRow(int [][] intArray){
        if (intArray.length < 2){
            return -1;
        }
        if (intArray[1].length == 0) {
            return -1;
        }
        int secondRowSum = 0;
        for (int rowNumber : intArray[1]) {
            secondRowSum += rowNumber;
        }
        return secondRowSum;

        //or
//        return Arrays.stream(intArray[1])
//                .sum();
    }
}
