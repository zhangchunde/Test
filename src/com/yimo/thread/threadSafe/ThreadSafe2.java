package com.yimo.thread.threadSafe;

public class ThreadSafe2 implements Runnable {
	
	public ThreadSafe2(Counter c){
		counter = c;
	}
	
	private Counter counter;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 50; i ++){
			if(this.counter != null){
				this.counter.add(1);
				System.out.println(Thread.currentThread().getName() + ": i = " + i + "; count = " + this.counter.getCount());
//				try {
//					counter.wait();
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
