package ru.myhw.task7.barrier;

public class Barrier {

    private final int maxThreadCount;
    private int threadCount;

    public Barrier(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
        threadCount = 0;
    }

    public synchronized void await() {
        threadCount++;
        if (threadCount == maxThreadCount) {
            notifyAll();
            return;
        }
        while (threadCount < maxThreadCount) {
            sleep();
        }
    }

    private void sleep() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
