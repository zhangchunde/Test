package com.yimo.thread.Threadpool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T07_ScheduledPool {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        service.scheduleAtFixedRate(()->{

                int time = new Random().nextInt(1000);
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-MM-dd hh:mm:ss sss");
                System.out.println("begin : " + System.currentTimeMillis());
//                TimeUnit.MILLISECONDS.sleep(time);
//                System.out.println("after sleep " + time + "ms.");
//                System.out.println("end : " + System.currentTimeMillis());

        },0,1000, TimeUnit.MILLISECONDS);


    }

}
