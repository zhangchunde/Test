package com.yimo.thread.threadLock;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class T12_TestExchanger {

    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args){
        new Thread(()->{
            String tool = "锤子";
            for (int i = 0; i < 5; i++) {
                int random = (int)(Math.random() * 1000);
                try {
                    System.out.println("工人" + Thread.currentThread().getName() + "正在使用" + tool + "...");
                    Thread.sleep(random);
                    System.out.println("工人" + Thread.currentThread().getName() + "使用" + tool + "结束，耗时" + random + "ms。");
                    System.out.println("工人" + Thread.currentThread().getName() + "等待使用其他工具。");
                    tool = exchanger.exchange(tool);
                    System.out.println("工人" + Thread.currentThread().getName() + "拿到" + tool + "。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"小王").start();

        new Thread(()->{
            String tool = "扳手";
            for (int i = 0; i < 5; i++) {
                int random = (int)(Math.random() * 1000);
                try {
                    System.out.println("工人" + Thread.currentThread().getName() + "正在使用" + tool + "...");
                    Thread.sleep(random);
                    System.out.println("工人" + Thread.currentThread().getName() + "使用" + tool + "结束，耗时" + random + "ms。");
                    System.out.println("工人" + Thread.currentThread().getName() + "等待使用其他工具。");
                    tool = exchanger.exchange(tool);
                    System.out.println("工人" + Thread.currentThread().getName() + "拿到" + tool + "。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"小李").start();
    }

}
