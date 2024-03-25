package com.galaxy13;

import com.galaxy13.homework14.FillArray;

public class Main {
    private static void outTime(long execTimeMillis) {
        System.out.println("Time: " + execTimeMillis / 1000.0 + "s");
    }

    public static void main(String[] args) {
        outTime(FillArray.fillOneThread());
        outTime(FillArray.fillMultiThread());
        try {
            outTime(FillArray.fillMultiThreadLatch());
        } catch (InterruptedException e) {
            System.out.println("Multi-thread operation error. " + e.getMessage());
        }
        outTime(FillArray.fillThreadPool());
        outTime(FillArray.fillFuture());
    }
}
