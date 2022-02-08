package com.yimo.thread.ThreadPerformance;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
//	private int c;
	private AtomicInteger c;
	private Counter(int i){
		if(c == null){
			c = new AtomicInteger();
		}
		c.set(i);
	}
	
	public int getCount(){
		return c.get();
	}
	
	public void setCount(int i){
		c.set(i);
	}
	
	public void add(int i){
		c.incrementAndGet();

	}
	public void addS(int i){
		String s = "synTest1";
		synchronized(s){
			c.incrementAndGet();
		}
	}
}
