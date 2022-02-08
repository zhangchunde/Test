package com.yimo.thread.threadLock.OneQuestion;

import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class ProductStorage {

    private static final int max = 50;
    LinkedList<Object> linkedList = new LinkedList<>();
//    ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
//    Lock readLock = readWriteLock.readLock();
//    Lock writeLock = readWriteLock.writeLock();
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    private Condition productor = reentrantLock.newCondition();
    private Condition consumer = reentrantLock.newCondition();

    public boolean putIn(String name,int m){
        reentrantLock.lock();
        int stock = linkedList.size();

        if(stock + m > max){
            System.out.println("[" + name + "]生产失败：库存[" + stock + "]+产量[" + m + "] = [" + (stock + m) + "]已经超过最大容量[" + max + "]。");
            try {
                productor.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
            return false;

        }

        for (int i = 0; i < m; i++) {
            Object o = new Object();
            linkedList.add(o);
        }
        System.out.println("[" + name + "]生产成功：原库存[" + stock + "]+产量[" + m + "] = [" + (stock + m) + "]未超过最大容量[" + max + "]。");
        consumer.signalAll();
        reentrantLock.unlock();
        return true;
    }

    public boolean putOut(String name, int m){
        reentrantLock.lock();
        int stock = linkedList.size();

        if(m > stock){
            System.out.println("[" + name + "]消费失败：库存[" + stock + "]-消费[" + m + "] = [" + (stock - m) + "]，库存不足。");
            try {
                consumer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
            return false;
        }

        for (int i = 0; i < m; i++) {
            linkedList.removeFirst();
        }
        System.out.println("[" + name + "]消费成功：库存[" + stock + "]-消费[" + m + "] = [" + (stock - m) + "]，库存充足。");
        productor.signalAll();
        reentrantLock.unlock();
        return true;
    }

    public int getStock(){
        int stock = linkedList.size();
        return stock;
    }

    public static void main(String[] args){

        ProductStorage productStorage = new ProductStorage();

        for (int i = 0; i < 2; i++) {
            new Thread(()->{

                while (true){
                    int random = 0;
                    do {
                        random = (int) (Math.random() * 50);
                    }while(random <= 0);

                    productStorage.putIn(Thread.currentThread().getName(), random);

                    try {
                        Thread.sleep((int)(Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            },"productor:" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(true){
                    int random = 0;
                    do {
                        random = (int)(Math.random() * 3);
                    }while(random <= 0);
                    productStorage.putOut(Thread.currentThread().getName(), random);
                    try {
                        Thread.sleep((int)(Math.random() * 3000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"consumer:" + i).start();
        }


    }


}
