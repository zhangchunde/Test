package com.yimo.thread.Threadpool;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class T02_CompletableFuture {

    public static void dalay(){
        int time = new Random().nextInt(500);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("After " + time + " ms sleep.");
    }

    public static double priceofTm(){
        dalay();
        return 3.02;
    }

    public static double priceofJd(){
        dalay();
        return 2.5;
    }

    public static double priceofPdd(){
        dalay();
//        return 2.1;
        throw new RuntimeException("product not exist!");
    }

    public static void main(String[] args) {

        long start, end;

        start = System.currentTimeMillis();

        CompletableFuture<Double> futureTm = CompletableFuture.supplyAsync(()->priceofTm());
        CompletableFuture<Double> futureJd = CompletableFuture.supplyAsync(()->priceofJd());
        CompletableFuture<Double> futurePdd = CompletableFuture.supplyAsync(()->priceofPdd());

        CompletableFuture.allOf(futureJd, futurePdd, futureTm).join();

        try {
            System.out.println("TM:" + futureTm.get() + "\nJD:" + futureJd.get() + "\nPDD:" + futurePdd.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        end = System.currentTimeMillis();

        System.out.println("end - start:" + (end - start));

    }

}
