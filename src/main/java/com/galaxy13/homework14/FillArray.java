package com.galaxy13.homework14;


import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FillArray {
    private static final double[] DOUBLES = new double[100_000_000];
    private static final int FOURTH_PART = DOUBLES.length / 4;

    private static void fillArray(int start, int finish) {
        for (int i = start; i < finish; i++) {
            DOUBLES[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }

    public static long fillOneThread() {
        Instant start = Instant.now();
        fillArray(0, DOUBLES.length);
        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    public static long fillMultiThreadLatch() throws InterruptedException {
        Instant startMillis = Instant.now();
        CountDownLatch latch = new CountDownLatch(4);

        for (int j = 0; j < 4; j++) {
            final int start = FOURTH_PART * j;
            final int finish = FOURTH_PART * (j + 1) - 1;
            Thread.startVirtualThread((() -> { // virtual threads of Java 21
                fillArray(start, finish);
                latch.countDown();
            }
            ));
        }
        latch.await();
        Instant finishMillis = Instant.now();
        return Duration.between(startMillis, finishMillis).toMillis();
    }

    public static long fillMultiThread() {
        Instant startMillis = Instant.now();
        List<Thread> threads = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            final int start = FOURTH_PART * i;
            final int finish = FOURTH_PART * (i + 1) - 1;
            Thread thread = Thread.startVirtualThread(() -> fillArray(start, finish));
            threads.add(thread);
        }
        threads.forEach(x -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        Instant finishMillis = Instant.now();
        return Duration.between(startMillis, finishMillis).toMillis();
    }

    public static long fillThreadPool() {
        Instant startMillis = Instant.now();
        try (ExecutorService threadPool = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 4; i++) {
                int start = FOURTH_PART * i;
                int finish = FOURTH_PART * (i + 1) - 1;
                threadPool.execute(() -> fillArray(start, finish));
            }
        }
        Instant finishMillis = Instant.now();
        return Duration.between(startMillis, finishMillis).toMillis();
    }

    public static long fillFuture() {
        Instant startMillis = Instant.now();
        List<CompletableFuture<Void>> futures = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            final int start = FOURTH_PART * i;
            final int finish = FOURTH_PART * (i + 1) - 1;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> fillArray(start, finish));
            futures.add(future);
        }
        futures.forEach(CompletableFuture::join);
        Instant finishMillis = Instant.now();
        return Duration.between(startMillis, finishMillis).toMillis();
    }
}
