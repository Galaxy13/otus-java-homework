package com.galaxy13;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static void outDuration(long millis, String thread) {
        System.out.println("Time exec: " + millis + "ms " + thread);
    }

    private static List<Integer> generateRandomIntegerList(int capacity, int maxNumber) {
        Random rand = new Random();
        List<Integer> list = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            int randomNumber = rand.nextInt(maxNumber);
            list.add(randomNumber);
        }
        return list;
    }
    public static void main(String[] args) {
        List<Integer> testList1 = generateRandomIntegerList(10_000_000, 100_000);
        outDuration(MultiThreadMerge.mergeSortMultiThread(testList1), "multi");
//        System.out.println(testList1);
        List<Integer> testList2 = generateRandomIntegerList(10_000_000, 100_000);
        outDuration(MultiThreadMerge.mergeSortSingleThread(testList2), "single");
//        System.out.println(testList2);
    }
}