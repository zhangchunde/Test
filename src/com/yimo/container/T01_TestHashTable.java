package com.yimo.container;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class T01_TestHashTable {
//    static Hashtable<UUID, UUID> m = new Hashtable<>();
//    static HashMap<UUID, UUID> m = new HashMap<UUID,UUID>();
    static Map<UUID, UUID> m = Collections.synchronizedMap(new HashMap<UUID,UUID>());
//    static Map<UUID, UUID> m = new ConcurrentHashMap<>();

    static int count = Constants.count;
    static UUID[] key = new UUID[count];
    static UUID[] value = new UUID[count];
    static int threadCount= Constants.threadCount;

    static{
        for (int i = 0; i < count; i++) {
            key[i] = UUID.randomUUID();
            value[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread{
        private int start = 0;
        private int gap = count/threadCount;
        MyThread(int start){
            this.start = start;
        }

        public void run(){

            for (int i = start; i < start + gap; i++) {

                m.put(key[i], value[i]);

            }

        }

    }

    public static void main(String[] args){

        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MyThread(i*(count/threadCount));
        }


        for (Thread t:threads) {
            t.start();
        }

        for (Thread t:threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("写入HashTable耗时：" + (endTime - startTime) + "ms, 共" + m.size() + "条数据。");


        startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i ++) {

            threads[i] = new Thread(()->{

                for (int j = 0; j < count; j++) {
                    m.get(key[j]);
                }

            });

        }


        for (Thread t:threads) {
            t.start();
        }

        for (Thread t:threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        endTime = System.currentTimeMillis();

        System.out.println("100个线程遍历HashTble耗时：" + (endTime - startTime) + "ms。");

    }


}
