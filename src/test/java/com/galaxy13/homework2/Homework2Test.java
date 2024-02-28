package com.galaxy13.homework2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Homework2Test {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    @BeforeAll
    public static void setOutStream(){
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void revertOutStream(){
        System.setOut(originalOut);
    }

    private static void checkOutEquality(String expectedOut){
        assertEquals(expectedOut, outContent.toString().trim());
        outContent.reset();
    }

    @Test
    public void testPrintNTimes(){
        Homework2.printNTimes(3, "Otus");
        checkOutEquality("Otus\nOtus\nOtus");

        Homework2.printNTimes(0, "Otus");
        checkOutEquality("");

        Homework2.printNTimes(5, "");
        checkOutEquality("");
    }

    @Test
    public void testSumBiggerThanFive(){
        Homework2.sumFiveOrBigger(new int[] {0, 2, 5, 10, 15});
        checkOutEquality("Sum of elements bigger than five: 25");

        Homework2.sumFiveOrBigger(new int[] {});
        checkOutEquality("Sum of elements bigger than five: 0");

        Homework2.sumFiveOrBigger(new int[] {20});
        checkOutEquality("Sum of elements bigger than five: 20");
    }

    @Test
    public void testIncrementArrayOnNumber(){
        int[] testIncArr1 = {0, 0, 1, 65366};
        Homework2.incrementArrayOnNumber(1, testIncArr1);
        assertArrayEquals(new int[] {1, 1, 2, 65367}, testIncArr1);

        int[] testIncArr2 = {};
        Homework2.incrementArrayOnNumber(2, testIncArr2);
        assertArrayEquals(new int[] {}, testIncArr2);

        int[] testIncArr3 = {3, 4, 5,};
        Homework2.incrementArrayOnNumber(-2, testIncArr3);
        assertArrayEquals(new int[] {1, 2, 3}, testIncArr3);
    }

    @Test
    public void testFillArrayWithNumber(){
        int[] testFillArr1 = {};
        Homework2.fillWithNumber(1, testFillArr1);
        assertArrayEquals( new int[] {}, testFillArr1);

        int[] testFillArr2 = {1,1,1,1};
        Homework2.fillWithNumber(0, testFillArr2);
        assertArrayEquals(new int[] {0, 0, 0, 0}, testFillArr2);

        int[] testFillArr3 = {10};
        Homework2.fillWithNumber(2, testFillArr3);
        assertArrayEquals(new int[] {2}, testFillArr3);
    }

    @Test
    public void testWhichHalfSumBigger(){
        int[] halfSumTestArr1 = new int[] {0, 1, 1, 0};
        Homework2.whichHalfSumBigger(halfSumTestArr1);
        checkOutEquality("Array half's sums are equal");

        int[] halfSumTestArr2 = new int[] {10, 5, 10};
        Homework2.whichHalfSumBigger(halfSumTestArr2);
        checkOutEquality("Second half is bigger with value: 15");

        int[] halfSumTestArr3 = new int[] {0, 1, 2, 3};
        Homework2.whichHalfSumBigger(halfSumTestArr3);
        checkOutEquality("Second half is bigger with value: 5");

        int[] halfSumTestArr4 = new int[] {12, 10, 0, -3};
        Homework2.whichHalfSumBigger(halfSumTestArr4);
        checkOutEquality("First half is bigger with value: 22");

        int[] halfSumTestArr5 = new int[] {1, 1};
        Homework2.whichHalfSumBigger(halfSumTestArr5);
        checkOutEquality("Array half's sums are equal");

        int[] halfSumTestArr6 = new int[] {0, 1};
        Homework2.whichHalfSumBigger(halfSumTestArr6);
        checkOutEquality("Second half is bigger with value: 1");

        int[] halfSumTestArr7 = new int[] {1, 0};
        Homework2.whichHalfSumBigger(halfSumTestArr7);
        checkOutEquality("First half is bigger with value: 1");

        int[] halfSumTestArr8 = new int[] {};
        Homework2.whichHalfSumBigger(halfSumTestArr8);
        checkOutEquality("Not enough length to split array on 2 half's (zero or one)");

        int[] halfSumTestArr9 = new int[] {54};
        Homework2.whichHalfSumBigger(halfSumTestArr9);
        checkOutEquality("Not enough length to split array on 2 half's (zero or one)");
    }
}
