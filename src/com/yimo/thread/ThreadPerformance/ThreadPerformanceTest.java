package com.yimo.thread.ThreadPerformance;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class ThreadPerformanceTest {
	static long count1 = 0L;
	static AtomicLong count2 = new AtomicLong(0L);
	static LongAdder count3 = new LongAdder();
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread[] threads = new Thread[1000];
		
//		Counter c = new Counter(1);
		
		final Object lock = new Object();
		
		for(int i = 0; i < threads.length; i ++){
			threads[i] = new Thread(){
				public void run() {
					for(int j = 0; j < 100000; j ++){
						synchronized(lock){
							count1 ++;
						}
					}
				}
			};
		}
		
		long start = System.currentTimeMillis();
		
		for(Thread t:threads) {t.start(); t.join();}
		
//		for(Thread t:threads) t.start();
//		
//		for(Thread t:threads) t.join();
		
//		while(count1 < 10000000){
//			
//		}
		long end = System.currentTimeMillis();
		System.out.println("Sync[end-start:" + (end - start) + ",count:" + count1 + "]" );
		
		
		for(int i = 0; i < threads.length; i ++){
			threads[i] = new Thread(){
				public void run() {
					for(int j = 0; j < 100000; j ++){
						count2.incrementAndGet();
					}
				}
			};
		}
		
		start = System.currentTimeMillis();
		
		for(Thread t:threads) {t.start(); t.join();}
		
//		for(Thread t:threads) t.start();
//		
//		for(Thread t:threads) t.join();
		
//		while(count1 < 10000000){
//			
//		}
		
		end = System.currentTimeMillis();
		
		System.out.println("Atomic[end-start:" + (end - start) + ",count:" + count2.get() + "]" );
		
		
		
		for(int i = 0; i < threads.length; i ++){
			threads[i] = new Thread(){
				public void run() {
					for(int j = 0; j < 100000; j ++){
						synchronized(this){
							count3.increment();
						}
					}
				}
			};
		}
		
		start = System.currentTimeMillis();
		
		for(Thread t:threads) {t.start(); t.join();}
		
//		for(Thread t:threads) t.start();
//		
//		for(Thread t:threads) t.join();
		
//		while(count1 < 10000000){
//			
//		}
		
		end = System.currentTimeMillis();
		
		System.out.println("Adder[end-start:" + (end - start) + ",count:" + count3.longValue() + "]" );
			
		

	}

}
