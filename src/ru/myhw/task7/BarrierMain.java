package ru.myhw.task7;

import ru.myhw.task7.barrier.Barrier;

public class BarrierMain {

    public static void main(String[] args) {
        int n = 5;
        Barrier barrier = new Barrier(n);
        Thread[] threads = new Thread[n];

        for (int i = 0; i < n; ++i) {
            int threadNum = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(threadNum);
                    try {
                        Thread.sleep(1000 * threadNum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    barrier.await();
                    System.out.printf("end %d\n", threadNum);
                }
            });
            threads[i].start();
        }
    }
}
