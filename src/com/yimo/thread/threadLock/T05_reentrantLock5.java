package com.yimo.thread.threadLock;

import java.util.concurrent.locks.ReentrantLock;

public class T05_reentrantLock5 extends Thread{
    private static ReentrantLock lock = new ReentrantLock(true);

    public void run() {
        for(int i=0; i<100; i++) {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + ",i:" + i +",lock");
            }finally{
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        T05_reentrantLock5 rl=new T05_reentrantLock5();
        Thread th1=new Thread(rl);
        Thread th2=new Thread(rl);
        Thread th3=new Thread(rl);
        th1.start();
        th2.start();
        th3.start();
    }


}
