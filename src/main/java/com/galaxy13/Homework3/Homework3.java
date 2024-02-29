package com.galaxy13.Homework3;

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

    public static void diagonalsZeroing(int[][] intArray, DiagonalType type) throws Exception{
        if (intArray.length == 0){
            throw new Exception("Empty array");
        }
        else if (intArray[0].length == 0){
            throw new Exception("Empty array");
        }
        for (int[] innerArray: intArray){
            if (innerArray.length != intArray.length){
                throw new Exception("Array is not symmetric. Diagonal not defined");
            }
        }
        switch (type){
            case BOTH -> {
                CaseCheck bothDiagonals = (row, col) -> (row == col || intArray.length - col - 1 == row);
                loopCase(intArray, bothDiagonals);
            }
            case MAIN_DIAGONAL -> {
                CaseCheck mainDiagonal = (row, col) -> (row == col);
                loopCase(intArray, mainDiagonal);
            }
            case SECONDARY_DIAGONAL -> {
                CaseCheck secondaryDiagonal = (row, col) -> intArray.length - col - 1  == row;
                loopCase(intArray, secondaryDiagonal);
            }
        }
    }

    public static int findMax(int[][] intArray) throws Exception{
        if (intArray.length == 0){
            throw new Exception("Empty array. No max element");
        }
        int max_element = Integer.MIN_VALUE;
        for (int[] innerIntArray : intArray) {
            for (int col = 0; col < intArray.length; col++) {
                max_element = Integer.max(max_element, innerIntArray[col]);
            }
        }
        return max_element;

//        or
//        OptionalInt optionalInt = Arrays.stream(intArray)
//                .flatMapToInt(Arrays::stream)
//                .max();
//        if (optionalInt.isPresent()){
//            return optionalInt.getAsInt();
//        } else {
//            throw new Exception("Empty array. No max element");
//        }
    }

    public static int sumOfSecondRow(int [][] intArray){
        if (intArray.length < 2){
            return -1;
        }
        int[] secondRow = intArray[1];
        int secondRowSum = 0;
        for (int rowNumber: secondRow){
            secondRowSum += rowNumber;
        }
        return secondRowSum;

        //or
//        return Arrays.stream(intArray[1])
//                .sum();
    }
}
