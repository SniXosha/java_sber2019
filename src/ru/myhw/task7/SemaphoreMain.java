package ru.myhw.task7;

import ru.myhw.task7.semaphor.Semaphore;

public class SemaphoreMain {

    public static void main(String[] args) {
        int maxThreadCount = 3;
        int n = 5;
        Semaphore semaphore = new Semaphore(maxThreadCount);

        Thread[] threads = new Thread[n];

        for (int i = 0; i < n; ++i) {
            int ind = i;
            threads[i] = new Thread(() -> {
                semaphore.lock();
                System.out.println(ind);
                try {
                    Thread.sleep(2000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.unlock();
            });
            threads[i].start();
        }
    }
}
