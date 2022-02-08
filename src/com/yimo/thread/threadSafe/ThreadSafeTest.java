package com.yimo.thread.threadSafe;

public class ThreadSafeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		testJoin();
		testThreadState();
//		testThreadSafe();
	}
	
	public static void testThreadSafe(){
		Counter counter = new Counter(0);
		Thread myThread1 = new Thread(new ThreadSafe(counter),"mythread1");
		myThread1.start();
		Thread myThread2 = new Thread(new ThreadSafe2(counter),"mythread2");
		myThread2.start();
	}
	
	public static void testJoin(){
		Counter counter = new Counter(0);
		Thread myThread1 = new Thread(new ThreadSafe(counter),"mythread1");
		myThread1.start();
		Thread myThread2 = new Thread(new ThreadSafe1(counter, myThread1),"mythread2");
		myThread2.start();
	}
	
	public static void testThreadState(){
		Counter counter = new Counter(0);
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Thread-begin!");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Thread-end!");
			}
			
		},"T1");
		
		System.out.println(t.getState());
		t.start();
		System.out.println(t.getState());
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(t.getState());
		
		
		
		new Thread(()->{
			System.out.println("Hello Lambda!");
			}).start();

	}

}
