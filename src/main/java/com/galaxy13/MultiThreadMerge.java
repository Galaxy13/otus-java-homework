package com.galaxy13;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadMerge {

    static int availibleTreads = Runtime.getRuntime().availableProcessors();

    private static void mergeSortMultiThreadInner(List<Integer> integerList, int left, int right) throws InterruptedException {
        if (left < right) {
            if (availibleTreads <= 1) {
                mergeSort(integerList, left, right);
            } else {
                int middle = left + (right - left) / 2;
                Thread leftThread = new Thread(() -> {
                    try {
                        availibleTreads--;
                        mergeSortMultiThreadInner(integerList, left, middle);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                Thread rightThread = new Thread(() -> {
                    try {
                        availibleTreads--;
                        mergeSortMultiThreadInner(integerList, middle + 1, right);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                leftThread.start();
                rightThread.start();
                try {
                    leftThread.join();
                    rightThread.join();
                } catch (InterruptedException err) {
                    System.out.println(err.getMessage());
                }
                availibleTreads += 2;
                merge(integerList, left, middle, right);
            }
        }
    }

    public static void mergeSort(List<Integer> integerList, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(integerList, left, middle);
            mergeSort(integerList, middle + 1, right);
            merge(integerList, left, middle, right);
        }
    }

    private static void merge(List<Integer> integerList, int left, int middle, int right) {
        List<Integer> leftPart = new ArrayList<>(integerList.subList(left, middle + 1));
        List<Integer> rightPart = new ArrayList<>(integerList.subList(middle + 1, right + 1));

        int leftInner = 0, rightInner = 0;
        while (leftInner < leftPart.size() && rightInner < rightPart.size()) {
            int firstValue, secondValue;
            if ((firstValue = leftPart.get(leftInner)) <= (secondValue = rightPart.get(rightInner))) {
                integerList.set(left, firstValue);
                leftInner++;
            } else {
                integerList.set(left, secondValue);
                rightInner++;
            }
            left++;
        }

        while (leftInner < leftPart.size()) {
            integerList.set(left, leftPart.get(leftInner));
            left++;
            leftInner++;
        }

        while (rightInner < rightPart.size()) {
            integerList.set(left, rightPart.get(rightInner));
            left++;
            rightInner++;
        }
    }

    public static long mergeSortMultiThread(List<Integer> integerList) {
        Instant start = Instant.now();
        Thread thread = new Thread(() -> {
            try {
                mergeSortMultiThreadInner(integerList, 0, integerList.size() - 1);
            } catch (InterruptedException e) {
                System.out.println("Multi-thread merge error");
            }
        });
        try {
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread start error");
        }
        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    public static long mergeSortSingleThread(List<Integer> integerList) {
        Instant start = Instant.now();
        mergeSort(integerList, 0, integerList.size() - 1);
        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }
}
