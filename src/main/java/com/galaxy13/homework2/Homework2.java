package com.galaxy13.homework2;

public class Homework2 {
    public static void printNTimes(int n, String str){
        System.out.println((str + "\n").repeat(n).trim());
    }

    public static void sumFiveOrBigger(int[] intArray){
        int arrSumBiggerFive = 0;
        for (int arrNumber: intArray){
            if (arrNumber > 5) arrSumBiggerFive += arrNumber;
        }
        System.out.print("Sum of elements bigger than five: ");
        System.out.println(arrSumBiggerFive);

        //or
        //System.out.println(Arrays.stream(intArray).filter(x -> x > 5).sum());
    }

    public static void fillWithNumber(int number, int[] intArray){
        for (int i=0; i < intArray.length; i++){
            intArray[i] = number;
        }

        // or
        // Arrays.fill(intArray, number);

        /* can be replaced with Arrays.stream().map(x -> number),
        but this method create new array instead of modifying
         */
    }

    public static void incrementArrayOnNumber(int number, int[] intArray){
        for (int i=0; i < intArray.length; i++){
            intArray[i] += number;
        }

        /* can be replaced with Arrays.stream().map(x -> x + number),
        but this method create new array instead of modifying existing
         */
    }

    public static void whichHalfSumBigger(int[] intArray){
        /*
        Пояснение: так как в задании не было отражено явно, как действовать, если количество элементов
        массива нечётное, алгоритм в данном случае центр массива считает 2 половиной.
        first|second
             |
        [1   |2    3]
         */
        if (intArray.length < 2){
            System.out.println("Not enough length to split array on 2 half's (zero or one)");
            return;
        }
        int median = intArray.length / 2;
        int firstHalfSum = 0, secondHalfSum = 0;
        for (int i=0; i < intArray.length; i++){
            if (i < median){
                firstHalfSum += intArray[i];
            } else {
                secondHalfSum += intArray[i];
            }
        }
        if (firstHalfSum > secondHalfSum){
            System.out.print("First half is bigger with value: ");
            System.out.println(firstHalfSum);
        } else if (firstHalfSum < secondHalfSum){
            System.out.print("Second half is bigger with value: ");
            System.out.println(secondHalfSum);
        } else {
            System.out.println("Array half's sums are equal");
        }
    }
}
