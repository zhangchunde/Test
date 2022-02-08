package com.yimo.thread.threadLock.OneQuestion;

import java.util.concurrent.TimeUnit;

public class T01_WithoutVolatile {

    public static void main(String[] args) {
        OneCollection oneCollection = new OneCollection();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                Object o = new Object();
                oneCollection.add(o);
                System.out.println("add:" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(()->{
//            do{
//                System.out.println(oneCollection.getSize());
//            }while(oneCollection.getSize() != 5);

            while(true){
//                System.out.println(oneCollection.getSize());
//                int i = oneCollection.getSize();
//                System.out.println(i);

                if(oneCollection.getSize() == 5){
                    break;
                }
            }

            System.out.println("Got it.");

        }).start();

    }





}
