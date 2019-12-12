package ru.myhw.task7.semaphor;

public class Semaphore {

    private final int maxThreadCount;
    private int currentThreadCount;

    public Semaphore(int maxThreadCount) {
        currentThreadCount = 0;
        this.maxThreadCount = maxThreadCount;
    }

    public synchronized void lock() {
        while (currentThreadCount == maxThreadCount) {
            await();
        }
        currentThreadCount++;
    }

    public synchronized void unlock() {
        currentThreadCount -= 1;
        this.notify();
    }

    private void await() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
