package com.yimo.thread.Threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T01_FutureTask {

    public static void main(String[] args) {

        FutureTask<String> task = new FutureTask<String>(()->{
            TimeUnit.SECONDS.sleep(10);
            return "OK";
        });

        new Thread(task).start();

        try {
            System.out.println(task.get());// BLOCKED, Wating for the thread to end.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
