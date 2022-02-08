package com.yimo.question1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class PrintByTurns {
    static Thread t1,t2;
    static char[] c = "abcdefghigklmnopqistuvwxyz".toCharArray();
    static String[] n = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26"};

    static volatile boolean running = false;

    enum ReadyToRun {T1,T2};

    static volatile ReadyToRun r = ReadyToRun.T1;

    static BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);

    static BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);

    static TransferQueue<String> tq = new LinkedTransferQueue<>();

    public static void main(String[] args) throws InterruptedException{

//        s01_LockSupport();

//        s01_SynWaitNotify();

//        s03_ReentranLock();

//        s04_Cas();

//        s05_BlockingQueue();

        s06_TransferQueue();

    }

    private static void s06_TransferQueue(){
        System.out.println("s06_TransferQueue");
        t1 = new Thread(()->{


            for (int i = 0; i < c.length; i++) {
                try {
                    System.out.println(tq.take());
                    tq.transfer(c[i] + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        });

        t2 = new Thread(()->{


            for (int i = 0; i < n.length; i++) {
                try {
                    tq.transfer(n[i]);
                    System.out.println(tq.take());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        });

        t2.start();
        t1.start();
    }

    private static void s05_BlockingQueue(){
        System.out.println("s05_BlockingQueue");
        t1 = new Thread(()->{


            for (int i = 0; i < c.length; i++) {
                System.out.println(c[i]);
                try {
                    q1.put("ok");
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        });

        t2 = new Thread(()->{


            for (int i = 0; i < n.length; i++) {
                try {
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(n[i]);
                try {
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        });

        t1.start();
        t2.start();
    }

    private  static void s04_Cas(){
        System.out.println("s04_Cas");
        t1 = new Thread(()->{


            for (int i = 0; i < c.length; i++) {
                while(r != ReadyToRun.T1){

                }
                System.out.println(c[i]);
                r = ReadyToRun.T2;

            }

        });

        t2 = new Thread(()->{


            for (int i = 0; i < n.length; i++) {
                while(r != ReadyToRun.T2){

                }
                System.out.println(n[i]);
                r = ReadyToRun.T1;

            }

        });

        t1.start();
        t2.start();
    }

    private static void s03_ReentranLock(){
        System.out.println("s03_ReentranLock");
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition c1 = reentrantLock.newCondition();
        Condition c2 = reentrantLock.newCondition();

        t1 = new Thread(()->{

            try {
                reentrantLock.lock();
                for (int i = 0; i < c.length; i++) {
                    System.out.println(c[i]);
                    c2.signal();
                    try {
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                c2.signal();
            }catch (Exception e){

            }finally {
                reentrantLock.unlock();
            }
        });

        t2 = new Thread(()->{

            try {
                reentrantLock.lock();
                for (int i = 0; i < n.length; i++) {
                    System.out.println(n[i]);
                    c1.signal();
                    try {
                        c2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                c1.signal();
            }catch (Exception e){

            }finally {
                reentrantLock.unlock();
            }
        });

        t2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
    }

    private static void s01_SynWaitNotify() throws InterruptedException{
        System.out.println("s01_SynWaitNotify");
        Object o = new Object();
        t1 = new Thread(()->{
            synchronized (o) {
                for (int i = 0; i < c.length; i++) {
                    System.out.println(c[i]);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }

        });

        t2 = new Thread(()->{
            synchronized (o) {

                for (int i = 0; i < n.length; i++) {
                    System.out.println(n[i]);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        });

        t1.start();
        t2.start();
    }

    private static void s01_LockSupport(){
        System.out.println("s01_LockSupport");
        t1 = new Thread(()->{

            if(running){
                if(!Thread.State.WAITING.equals(t1.getState())){
                    LockSupport.park();
                }
            }else{
                running = true;
            }

            for (int i = 0; i < c.length; i++) {
                System.out.println(c[i]);
                LockSupport.unpark(t2);
                LockSupport.park();
            }

            if(Thread.State.WAITING.equals(t2.getState())){
                LockSupport.unpark(t2);
            }

        });

        t2 = new Thread(()->{
            if(running){
                if(!Thread.State.WAITING.equals(t1.getState())){
                    LockSupport.park();
                }
            }else{
                running = true;
            }

            for (int i = 0; i < n.length; i++) {
                System.out.println(n[i]);
                LockSupport.unpark(t1);
                LockSupport.park();
            }

            if(Thread.State.WAITING.equals(t1.getState())){
                LockSupport.unpark(t1);
            }
        });

        t2.start();

        t1.start();
    }
}
