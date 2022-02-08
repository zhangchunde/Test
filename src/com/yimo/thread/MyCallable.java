package com.yimo.thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String>{

	@Override
	public String call() throws Exception {
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
		return "success";
	}

}
