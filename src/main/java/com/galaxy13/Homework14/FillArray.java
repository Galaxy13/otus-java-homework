package com.galaxy13.Homework14;


import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FillArray {
    private final double[] doubles = new double[100_000_000];
    private final int fourthPart = doubles.length / 4;

    private void fillArray(int start, int finish) {
        for (int i = start; i < finish; i++) {
            doubles[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
    }

    public long fillOneThread() {
        Instant start = Instant.now();
        fillArray(0, doubles.length);
        Instant finish = Instant.now();
        return Duration.between(start, finish).toMillis();
    }

    public long fillMultiThreadLatch() throws InterruptedException {
        Instant startMillis = Instant.now();
        CountDownLatch latch = new CountDownLatch(4);

        for (int j = 0; j < 4; j++) {
            final int start = fourthPart * j;
            final int finish = fourthPart * (j + 1) - 1;
            Thread.startVirtualThread((() -> { // green threads of Java 21
                fillArray(start, finish);
                latch.countDown();
            }
            ));
        }
        latch.await();
        Instant finishMillis = Instant.now();
        return Duration.between(startMillis, finishMillis).toMillis();
    }

    public long fillMultiThread() {
        Instant startMillis = Instant.now();
        List<Thread> threads = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            final int start = fourthPart * i;
            final int finish = fourthPart * (i + 1) - 1;
            Thread thread = Thread.startVirtualThread(() -> { // green threads of Java 21
                fillArray(start, finish);
            });
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

    public long fillThreadPool() {
        Instant startMillis = Instant.now();
        try (ExecutorService threadPool = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 4; i++) {
                int start = fourthPart * i;
                int finish = fourthPart * (i + 1) - 1;
                threadPool.execute(() -> fillArray(start, finish));
            }
        }
        Instant finishMillis = Instant.now();
        return Duration.between(startMillis, finishMillis).toMillis();
    }

    public long fillAsync() {
        Instant startMillis = Instant.now();
        List<CompletableFuture<Void>> futures = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            final int start = fourthPart * i;
            final int finish = fourthPart * (i + 1) - 1;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> fillArray(start, finish));
            futures.add(future);
        }
        futures.forEach(CompletableFuture::join);
        Instant finishMillis = Instant.now();
        return Duration.between(startMillis, finishMillis).toMillis();
    }
}
