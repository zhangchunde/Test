package com.yimo.thread.Threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolJPerformanceOptimization {

    static CountDownLatch countDownLatch = new CountDownLatch(50);

    static class ComputePrime implements Runnable {

        private int max_v;
        List<Integer> result = new ArrayList<Integer>();

        public ComputePrime(int max_v) {
            this.max_v = max_v;
        }

        @Override
        public void run() {
            Long s = System.currentTimeMillis();
            for (int i = 1; i <= max_v; i++) {
                if (isPrime(i)) {
                    result.add(i);
                }
            }
            Long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " is end; result:" + result.size() + "; cost: " + (e-s));
            countDownLatch.countDown();
        }

        private boolean isPrime(int value) {

            for (int i = 2; i < value / 2; i++) {

                if (value % i == 0) {
                    return false;
                }

            }

            return true;
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(50, 50, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        long s = System.currentTimeMillis();

        for (int i = 0; i < 50; i++) {
            tpe.execute(new ComputePrime(200000));
        }

        countDownLatch.await();

        long e = System.currentTimeMillis();

        System.out.println(e-s);

        tpe.shutdown();

    }

}
