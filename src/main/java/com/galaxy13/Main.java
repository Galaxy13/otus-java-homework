package com.galaxy13;

import com.galaxy13.Homework14.FillArray;

public class Main {
    private static void outTime(long execTimeMillis) {
        System.out.println("Time: " + ((double) execTimeMillis) / 1000.0 + "s");
    }

    public static void main(String[] args) {
        FillArray fillArray = new FillArray();
        outTime(fillArray.fillOneThread());
        outTime(fillArray.fillMultiThread());
        try {
            outTime(fillArray.fillMultiThreadLatch());
        } catch (InterruptedException e) {
            System.out.println("Multi-thread operation error. " + e.getMessage());
        }
        outTime(fillArray.fillThreadPool());
        outTime(fillArray.fillAsync());
    }
}
