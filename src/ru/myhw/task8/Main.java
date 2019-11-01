package ru.myhw.task8;

import ru.myhw.task8.concurrent.Task;

import java.util.concurrent.Callable;

class CalcSum implements Callable<Integer> {

    private final long n;
    private final boolean throwException;

    CalcSum(long n, boolean throwException) {
        this.n = n;
        this.throwException = throwException;
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (long i = 0; i < n; i++) {
             result += i;
        }
        if (throwException) throw new Exception("something went wrong");
        return result;
    }
}

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
