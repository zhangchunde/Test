package com.yimo.thread.threadLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class T07_TestCyclicBarrier {

    public static void main(String[] args){

        produceBeer();
//        packageBeer();

    }

    public static void produceBeer(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            System.out.println("1瓶啤酒生产完毕。");
        });

        CountDownLatch countDownLatch = new CountDownLatch(5);

        Thread[] threads = new Thread[5];
        String[] texts = new String[]{"生产瓶盖","生产瓶身","灌装","贴标","检验"};

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    int random = (int)(Math.random() * 100);
                    try {
                        Thread.sleep(random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("流水线" + Thread.currentThread().getName() + "工作完毕，耗时" + random + "ms，等待组装。");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    if(j < 99) {
                        System.out.println("流水线" + Thread.currentThread().getName() + "继续工作。");
                    }
                }

                countDownLatch.countDown();
                System.out.println("流水线" + Thread.currentThread().getName() + "完工。");

            },"Thread[" + texts[i] + "]");
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
            System.out.println("流水线" + threads[i].getName() + "开始干活...");
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("完活，下班！");
    }


    public static void packageBeer(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
            System.out.println("集满5瓶啤酒，装箱！");
        });

        CountDownLatch countDownLatch = new CountDownLatch(5);

        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    int randomInt = (int) (Math.random() * 100);
                    try {
                        Thread.sleep(randomInt);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("流水线" + Thread.currentThread().getName() + "生产1瓶啤酒，耗时" + randomInt + "ms。");
                    System.out.println("流水线" + Thread.currentThread().getName() + "等待装箱。");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    if(j < 99) {
                        System.out.println("流水线" + Thread.currentThread().getName() + "继续生产。");
                    }
                }

                countDownLatch.countDown();
                System.out.println("流水线" + Thread.currentThread().getName() + "完工");
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
            System.out.println("流水线" + threads[i].getName() + "开始干活...");
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("完活，下班！");

    }

}
