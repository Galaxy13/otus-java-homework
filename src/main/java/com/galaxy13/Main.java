package com.galaxy13;

import com.galaxy13.homework2.Homework2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Homework2.printNTimes(0, "Otus");
        Homework2.sumFiveOrBigger(new int[] {1, -1, 0, 5, 10, 15});
        int[] testArr = new int[] {1, 2, 4, 5, 10};
        Homework2.fillWithNumber(2, testArr);
        System.out.println(Arrays.toString(testArr));
        Homework2.incrementArrayOnNumber(1, testArr);
        System.out.println(Arrays.toString(testArr));
        int[] halfSumTestArr1 = new int[] {0, 1, 1, 0};
        int[] halfSumTestArr2 = new int[] {10, 5, 10};
        int[] halfSumTestArr3 = new int[] {0, 1, 2, 3};
        int[] halfSumTestArr4 = new int[] {12, 10, 0, -3};
        int[] halfSumTestArr5 = new int[] {1, 1};
        int[] halfSumTestArr6 = new int[] {0, 1};
        int[] halfSumTestArr7 = new int[] {1, 0};
        Homework2.whichHalfSumBigger(halfSumTestArr1);
        Homework2.whichHalfSumBigger(halfSumTestArr2);
        Homework2.whichHalfSumBigger(halfSumTestArr3);
        Homework2.whichHalfSumBigger(halfSumTestArr4);
        Homework2.whichHalfSumBigger(halfSumTestArr5);
        Homework2.whichHalfSumBigger(halfSumTestArr6);
        Homework2.whichHalfSumBigger(halfSumTestArr7);
    }
}