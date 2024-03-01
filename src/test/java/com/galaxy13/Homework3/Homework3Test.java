package com.galaxy13.Homework3;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Homework3Test {
    private static final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    @Test
    public void testSumOfPositive(){
        int [][] testArr1 = {{}};
        assertEquals(0, Homework3.sumOfPositiveElements(testArr1));

        int [][] testArr2 = {{1}};
        assertEquals(1, Homework3.sumOfPositiveElements(testArr2));

        int [][] testArr3 = {{1,2}, {3, 4}};
        assertEquals(10, Homework3.sumOfPositiveElements(testArr3));

        int [][] testArr4 = {{0}, {1, 3}, {-4}, {2, 2, 2}};
        assertEquals(10, Homework3.sumOfPositiveElements(testArr4));

        int [][] testArr5 = {{-1}};
        assertEquals(0, Homework3.sumOfPositiveElements(testArr5));
    }

    private void drawSquare(int squareSize, String expected){
        Homework3.starSquare(squareSize);
        assertEquals(expected, outStream.toString().trim());
        outStream.reset();
    }
    @Test
    public void testStarSquare(){
        System.setOut(new PrintStream(outStream));

        drawSquare(1, "*");
        drawSquare(0, "");
        drawSquare(2, "* * \n* *");

        System.setOut(originalOut);
    }

    private void testRightZeroing(int[][] arrPointer, int[][] expectedArr, DiagonalType type) throws Exception {
        Homework3.diagonalsZeroing(arrPointer, type);
        assertArrayEquals(expectedArr, arrPointer);
    }

    @Test
    public void testdiagonalZeroing() throws Exception {

       int[][] intArray1 = {};
       Exception exception1 = assertThrows(Exception.class, () -> Homework3.diagonalsZeroing(intArray1, DiagonalType.BOTH));
       assertEquals("Empty array", exception1.getMessage());

       int[][] intArray2 = {{}};
       Exception exception2 = assertThrows(Exception.class, () -> Homework3.diagonalsZeroing(intArray2, DiagonalType.MAIN_DIAGONAL));
        assertEquals("All inner arrays are empty.", exception2.getMessage());

        int[][] intArray3 = {{1, 2}};
        Exception exception3 = assertThrows(Exception.class, () -> Homework3.diagonalsZeroing(intArray3, DiagonalType.MAIN_DIAGONAL));
        assertEquals("Array is not symmetric. Diagonal not defined", exception3.getMessage());

        int[][] intArray4 = {{1}};
        testRightZeroing(intArray4, new int[][]{{0}}, DiagonalType.MAIN_DIAGONAL);

        int[][] intArray5 = {{1, 1}, {2, 4}};
        testRightZeroing(intArray5, new int[][]{{1, 0}, {0, 4}}, DiagonalType.SECONDARY_DIAGONAL);

        int[][] intArray6 = {{1, 3, -1}, {2, 5, 7}, {0, 2, 1}};
        testRightZeroing(intArray6, new int[][]{{0, 3, 0}, {2, 0, 7}, {0, 2, 0}}, DiagonalType.BOTH);
    }

    @Test
    public void testFindMax() throws Exception {
        int[][] intArray1 = {{}, {}, {}};
        Exception exception1 = assertThrows(Exception.class, () -> Homework3.findMax(intArray1));
        assertEquals("All inner arrays are empty.", exception1.getMessage());

        int[][] intArray2 = {};
        Exception exception2 = assertThrows(Exception.class, () -> Homework3.findMax(intArray2));
        assertEquals("Empty array", exception2.getMessage());

        int[][] intArray3 = {{0}};
        assertEquals(0, Homework3.findMax(intArray3));

        int[][] intArray4 = {{0, 1}, {-1, -2, 100}};
        assertEquals(100, Homework3.findMax(intArray4));

        int[][] intArray5 = {{1, 1, 1}, {1, 1, 0}, {-1}};
        assertEquals(1, Homework3.findMax(intArray5));
    }

    @Test
    public void testSumOfSecondRow() {
        int[][] intArray1 = {};
        assertEquals(-1, Homework3.sumOfSecondRow(intArray1));

        int[][] intArray2 = {{1, 2}, {}, {5, 2}};
        assertEquals(-1, Homework3.sumOfSecondRow(intArray2));

        int[][] intArray3 = {{}, {}};
        assertEquals(-1, Homework3.sumOfSecondRow(intArray3));

        int[][] intArray4 = {{}, {2}};
        assertEquals(2, Homework3.sumOfSecondRow(intArray4));

        int[][] intArray5 = {{1, 2}, {0, -1, 2, 3, 4}, {6, 7, 9}, {3, 5}};
        assertEquals(8, Homework3.sumOfSecondRow(intArray5));
    }
}
