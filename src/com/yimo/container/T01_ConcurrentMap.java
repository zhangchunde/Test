package com.yimo.container;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {

    public static void main(String[] args){
        Thread[] threads = new Thread[100];
        Random r = new Random();
        Map<String,String> map = new ConcurrentHashMap<>();
//        Map<String,String> map = new ConcurrentSkipListMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        long start = System.currentTimeMillis();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    map.put(Thread.currentThread().getName() + "a" + j,Thread.currentThread().getName() +  "b" + r.nextInt(100000));
                }
                countDownLatch.countDown();
            });
        }

        Arrays.asList(threads).forEach(t -> t.start());

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("end - start : " + (end - start) + "; map.size : " + map.size());


    }

}
