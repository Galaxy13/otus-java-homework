package com.galaxy13.Homework3;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void diagonalZeroing() throws Exception {

       int[][] intArray1 = {};
       Exception exception1 = assertThrows(Exception.class, () -> Homework3.diagonalsZeroing(intArray1, DiagonalType.BOTH));
       assertEquals("Empty array", exception1.getMessage());

       int[][] intArray2 = {{}};
       Exception exception2 = assertThrows(Exception.class, () -> Homework3.diagonalsZeroing(intArray2, DiagonalType.MAIN_DIAGONAL));
       assertEquals("Empty array", exception2.getMessage());
    }
}
