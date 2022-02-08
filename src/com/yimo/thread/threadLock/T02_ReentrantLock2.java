package com.yimo.thread.threadLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class T02_ReentrantLock2 {
    Lock lock = new ReentrantLock();
    void m1() {
        try {
            lock.lock(); //synchronized(this)
            for (int i = 0; i < 20; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    void m2() {
        boolean b = false;
        try {
//            lock.lock();
            b = lock.tryLock(6, TimeUnit.SECONDS);
            System.out.println("m2 ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(b) {
                System.out.println("lock!");
                lock.unlock();
            }else{
                System.out.println("unlock!");
            }
        }
    }

    void m3() {
//        boolean b = false;
        try {
//            lock.lock();
//            b = lock.tryLock(6, TimeUnit.SECONDS);
            lock.lockInterruptibly();
            System.out.println("m2 ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("interrupt ...");
        } finally {
            lock.unlock();
            System.out.println("unlock!");

        }
    }

    public static void main(String[] args) {
        T02_ReentrantLock2 rl = new T02_ReentrantLock2();
        new Thread(rl::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t3 = new Thread(rl::m3);
        t3.start();

        t3.interrupt();

    }
}
