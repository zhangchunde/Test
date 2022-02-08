package com.yimo.container;

import jdk.nashorn.internal.ir.Block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T01_ArrayBlockingQueue {

    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        System.out.println(queue.size());

        for (int i = 0; i < 10; i++) {
            queue.add("abc" + i);
        }

//        queue.add("s");
        boolean b = queue.offer("o");
        System.out.println(b);

//        queue.put("p");

        b = queue.offer("o",1, TimeUnit.SECONDS);
        System.out.println(b);

        System.out.println(queue);
    }

}
