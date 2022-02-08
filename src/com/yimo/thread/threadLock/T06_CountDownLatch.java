package com.yimo.thread.threadLock;

import java.util.concurrent.CountDownLatch;

public class T06_CountDownLatch {

    public static void main(String[] args){
        testCountDownLatch();
    }

    public static void testCountDownLatch(){
        Thread[] threads = new Thread[100];

        CountDownLatch countDownLatch = new CountDownLatch(threads.length);

        for(int i = 0; i < threads.length; i ++){
            threads[i] = new Thread(() -> {
                try {
                    int random = (int)(Math.random() * 100);
                    Thread.sleep(random);
                    System.out.println(Thread.currentThread().getName() + " sleeped " + random + "ms.");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"Thread-" + i);
        }

        for(int i = 0; i < threads.length; i ++){
            threads[i].start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch!");

    }
}
