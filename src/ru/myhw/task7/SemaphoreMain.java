package ru.myhw.task7;

import ru.myhw.task7.semaphor.Semaphore;

public class SemaphoreMain {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; ++i) {
            int finalI = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    semaphore.lock();
                    int i = 0;
                    while (i < 10) {
                        System.out.println(finalI);
                        try {
                            Thread.sleep(1000 );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        i++;
                    }
                    semaphore.unlock();
                }
            });
            threads[i].start();
        }
    }
}
