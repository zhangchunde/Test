package com.yimo.thread.threadLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T10_TestReadWriteLock {
//    static Lock lock = new ReentrantLock();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    static private String value;

    public static String read(Lock lock){
        lock.lock();
        String v = value;
        try {
            Thread.sleep(1000);
            System.out.println("read over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return v;
    }

    public static void write(Lock lock, String s){
        lock.lock();
        value = s;
        try {
            Thread.sleep(1000);
            System.out.println("write over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){

//        Runnable readRunnable = ()->read(lock);
        Runnable readRunnable = ()->read(readLock);

//        Runnable wirteRunnable = ()->write(lock,"a");
        Runnable wirteRunnable = ()->write(writeLock,"a");

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(wirteRunnable).start();
        }

    }

}
