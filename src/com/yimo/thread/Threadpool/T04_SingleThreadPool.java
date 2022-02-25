package com.yimo.thread.Threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T04_SingleThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final  int j = i;
            service.execute(()->{
                System.out.println(j + "-" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        service.shutdown();
    }

}
