package ru.myhw.task8;

import ru.myhw.task8.concurrent.Task;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long n = 5_000_000_000L;
        int numThreads = 5;
        Callable<Integer> callable = new CalcSum(n, false);
        Task<Integer> task = new Task<>(callable);

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; ++i) {
            threads[i] = new Thread(() -> {
                try {
                    System.out.println(task.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 0; i < numThreads; ++i) {
            threads[i].start();
        }
        for (int i = 0; i < numThreads; ++i) {
            threads[i].join();
        }
    }
}
