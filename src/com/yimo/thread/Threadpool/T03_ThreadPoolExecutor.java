package com.yimo.thread.Threadpool;


import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class T03_ThreadPoolExecutor {

    static class Task implements Runnable{

        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
//            int time = new Random().nextInt(1000);
            int time = 1000000;
            System.out.println("task [" + i + "] start ." );
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task [" + i + "] running for " + time + "ms." );
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("task [" + i + "] end.");
        }
    }

    public static void main(String[] args) {

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2,4,60, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(20), Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            tpe.execute(new Task(i));
        }

        System.out.println(tpe.getQueue().size());
        tpe.execute(new Task(20));
        System.out.println(tpe.getQueue().size());
        tpe.shutdown();

    }

}
