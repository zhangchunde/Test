package com.yimo.thread;

public class TestThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		createThread();
		(new TestThread()).createThread();
	}

	public void createThread(){
		new Thread(()->{
			System.out.println("Hello Lambda!");
		}).start();

		// TODO Auto-generated method stub
//		System.out.println("���߳�ThreadName:" + Thread.currentThread().getName());
//		MyThread myThread = new MyThread();
//		myThread.start();
//		Thread myThread2 = new Thread(new MyThreadRunnable(),"MyRunnable");
//		myThread2.start();

//		Thread t = new Thread(new FutureTask<String>(new MyCallable()),"MyCallable");
//		t.start();
//
//
//		for(int i = 0; i < 10; i ++){
//			new Thread("Thread" + i){
//				public void run(){
//					System.out.println("ThreadName:" + Thread.currentThread().getName());
//				}
//			}.start();
//		}
	}

}
