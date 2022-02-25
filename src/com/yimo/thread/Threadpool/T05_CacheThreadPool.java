package com.yimo.thread.Threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T05_CacheThreadPool {
    public static void main(String[] args) {
        ExecutorService cachePool = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            cachePool.execute(()->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(cachePool);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cachePool);

    }
}
