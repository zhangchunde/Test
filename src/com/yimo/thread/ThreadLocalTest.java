package com.yimo.thread;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {



    public static void main(String[] args){
        class Person{
            Person(){}
            Person(String s){
                name = s;
            }
            String name = "zhangsan";
        }
//        Person person = new Person();
        ThreadLocal<Person> personThreadLocal = new ThreadLocal<Person>();
        new Thread(()->{
            personThreadLocal.set(new Person());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(personThreadLocal.get().name);
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            personThreadLocal.set(new Person("lisi"));
        }).start();


    }

}
