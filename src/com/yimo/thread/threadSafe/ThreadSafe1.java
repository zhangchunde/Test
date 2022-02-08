package com.yimo.thread.threadSafe;

public class ThreadSafe1 implements Runnable {
	
	public ThreadSafe1(Counter c, Thread t){
		counter = c;
		thread = t;
	}
	
	private Counter counter;
	private Thread thread;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			thread.join(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 50; i ++){
			if(this.counter != null){
				this.counter.addS(1);
				System.out.println(Thread.currentThread().getName() + ": i = " + i + "; count = " + this.counter.getCount());
//				try {
//					counter.wait();
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				if(i == 20){
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
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
