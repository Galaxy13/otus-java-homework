package com.galaxy13;

import java.util.ArrayList;
import java.util.Iterator;
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

    private static boolean checkIfSorted(List<Integer> integerList) {
        if (integerList == null || integerList.size() == 1) {
            return true;
        }

        Iterator<Integer> iterator = integerList.iterator();
        Integer current;
        Integer previous = iterator.next();
        while (iterator.hasNext()) {
            current = iterator.next();
            if (previous.compareTo(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }
    public static void main(String[] args) {
        List<Integer> testList1 = generateRandomIntegerList(10_000_000, 100_000);
        outDuration(MultiThreadMerge.mergeSortMultiThread(testList1), "multi");
        System.out.println("List is sorted: " + checkIfSorted(testList1));
        List<Integer> testList2 = generateRandomIntegerList(10_000_000, 100_000);
        outDuration(MultiThreadMerge.mergeSortSingleThread(testList2), "single");
        System.out.println("List is sorted: " + checkIfSorted(testList2));
    }
}