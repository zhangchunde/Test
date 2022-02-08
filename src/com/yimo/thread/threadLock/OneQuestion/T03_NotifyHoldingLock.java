package com.yimo.thread.threadLock.OneQuestion;

public class T03_NotifyHoldingLock {



    public static void main(String[] args){

        OneCollection oneCollection = new OneCollection();

        Object lock = new Object();

        new Thread(()->{
            synchronized (lock){
                if(oneCollection.getSize() != 5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Got it!");
                    lock.notify();
                }
            }
        }).start();

        new Thread(()->{
            synchronized (lock){
                for (int i = 0; i < 10; i++) {
                    oneCollection.add(new Object());
                    System.out.println("add:" + i);
                    if(i == 4){
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }

}
