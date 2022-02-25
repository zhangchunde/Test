package com.yimo.thread.Threadpool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T09_WorkStealingPool {

    static class R implements Runnable{

        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + " " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws IOException {

        ExecutorService service = Executors.newWorkStealingPool();

        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(3000));
        service.execute(new R(5000));
        service.execute(new R(5000));
        service.execute(new R(5000));
        service.execute(new R(5000));
        service.execute(new R(5000));
        service.execute(new R(4500));
        service.execute(new R(4600));
        service.execute(new R(4700));

        System.in.read();

    }

}
