package com.yimo.thread.threadLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class T11_TestSemaphore {

    public static void main(String[] args){
//        testSemaphore();
        highwayOut();
    }

    public static void highwayOut(){
        Semaphore semaphore = new Semaphore(5,true);
        boolean isEnd = false;
        CountDownLatch countDownLatch = new CountDownLatch(100);

        new Thread(()->{
            do {
                System.out.println("滚动信息：目前有" + semaphore.getQueueLength() + "辆车等待下高速。共5个出口，其中" + semaphore.availablePermits() + "个出口空闲。");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while(countDownLatch.getCount()>0);
        }).start();

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    int random = (int)(Math.random() * 100000);
                    System.out.println("车辆[" + Thread.currentThread().getName() + "]在高速路上行驶...");
                    Thread.sleep(random);
                    System.out.println("车辆[" + Thread.currentThread().getName() + "]行驶" + random + "ms后，准备下高速。");
                    semaphore.acquire();
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                    System.out.println("车辆[" + Thread.currentThread().getName() + "]已经下高速。");
                    countDownLatch.countDown();
                }
            },"car" + i).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有车辆都已下高速！");
    }

    public static void testSemaphore(){
        Semaphore semaphore = new Semaphore(2);
//        Semaphore semaphore = new Semaphore(2, true);

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T1 is running!");
                Thread.sleep(1000);
                System.out.println("T1 is stoped!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T2 is running!");
                Thread.sleep(1000);
                System.out.println("T2 is stoped!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T3 is running!");
                Thread.sleep(1000);
                System.out.println("T3 is stoped!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("T4 is running!");
                Thread.sleep(1000);
                System.out.println("T4 is stoped!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();
    }
}
