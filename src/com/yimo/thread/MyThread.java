package com.yimo.thread;

public class MyThread extends Thread{
	public MyThread(){
//		System.out.println("构造方法ThreadName:" + Thread.currentThread().getName());
		this.setName("MyThread");
	}
	public void run(){
//		System.out.println("run方法ThreadName:" + Thread.currentThread().getName());
		
		for(int i = 0; i < 30; i ++){
			System.out.println(Thread.currentThread().getName() + "-Counter:" + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
