package com.yimo.thread;

public class MyThreadRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
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
