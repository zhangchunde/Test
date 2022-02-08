package com.yimo.thread;

import java.util.concurrent.TimeUnit;

public class TestVolatile {

    volatile boolean running = true;

    public void m(){

        long start = System.currentTimeMillis();

        while(running){
            System.out.println("running...");
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    public void setRunningFalse(){
        running = false;
    }

    static class T1 extends Thread{
        private TestVolatile tv;

        public T1(TestVolatile tv1){
            tv = tv1;
        }

        @Override
        public void run() {
            super.run();
            tv.m();
        }
    }

    static class T2 extends Thread{
        private TestVolatile tv;

        public T2(TestVolatile tv1){
            tv = tv1;
        }

        @Override
        public void run() {
            super.run();
            tv.setRunningFalse();
        }
    }

    public static void main(String[] args) {

        TestVolatile tv = new TestVolatile();

        T1 t1 = new T1(tv);
        T2 t2 = new T2(tv);

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

    }

}
