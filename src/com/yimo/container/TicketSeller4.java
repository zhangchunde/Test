package com.yimo.container;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller4 {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static{
        for (int i = 0; i < 10000; i++) {
            tickets.add("表编号" + i);
        }
    }

    public static void main(String[] args){

        for (int i = 0; i < 10; i++) {
            new Thread(()->{

                while(true){
                    String ticket = tickets.poll();

                    if(ticket == null) break;
                    else System.out.println("销售了--" + ticket);

                }

            }).start();
        }

    }

}
