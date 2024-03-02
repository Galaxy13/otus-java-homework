package com.galaxy13;

import com.galaxy13.Homework3.DiagonalType;
import com.galaxy13.Homework3.Homework3;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        int[][] arraySumPositive = {{1, 2}, {-1, 0, 5}};
        System.out.printf("Sum of positive elements: %s%n%n", Homework3.sumOfPositiveElements(arraySumPositive));

        Homework3.starSquare(5);

        int[][] arrayDiagonalZeroing = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Homework3.diagonalsZeroing(arrayDiagonalZeroing, DiagonalType.BOTH);
        System.out.println(Arrays.stream(arrayDiagonalZeroing)
                .map(Arrays::toString)
                .collect(Collectors.joining(System.lineSeparator())));

        int[][] arrayMax = {{2, 3,}, {5, 10, -25}};
        System.out.printf("%nMax element in array: %s%n%n", Homework3.findMax(arrayMax));

        int[][] arraySumSecondRow = {{}, {2, 5, 10}, {7, 10, -2}};
        System.out.printf("Sum of 2nd row of array: %s", Homework3.sumOfSecondRow(arraySumSecondRow));
    }
}