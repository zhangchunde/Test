package com.yimo.list;

public class MyStank {
	
	MyNode head;
	
	public synchronized MyNode pop(){
		if(head == null){
			return null;
		}
		
		MyNode next = head;
		
		this.head = head.getNext();
		
		return next;
	}
	
	public synchronized void push(MyNode node){
		if(node == null){
			return;
		}
		
		node.setNext(head);
		this.head = node;
	}
	
	public void ergodic(){
		MyNode cursor = this.head;
		
		int i = 0;
		
		while(cursor != null){
			System.out.println("count[" + i + "]:" + cursor.getContent());
			i ++;
			cursor = cursor.getNext();
		}
		
	}
	
	public static void main(String[] args){
		final MyStank myStank = new MyStank();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < 100; i ++){
					myStank.push(new MyNode(Thread.currentThread().getName() + ":" + i));
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		},"t1");
		
		t1.start();
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < 100; i ++){
					myStank.push(new MyNode(Thread.currentThread().getName() + ":" + i));
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		},"t2");
		
		t2.start();
		
		while(!"TERMINATED".equals(t1.getState().toString()) || !"TERMINATED".equals(t2.getState().toString())){
			System.out.println("wait...");
		}
		
		myStank.ergodic();
		
		System.out.println("start pop...");
		
		Thread t3 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MyNode node;
				int i = 0;
				
				node = myStank.pop();
				while(node != null){
					System.out.println("count[" + i + "]:" + node.getContent());
					i ++ ;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					node = myStank.pop();
				}
			}
			
		},"t3");
		
		t3.start();
		
		Thread t4 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MyNode node;
				int i = 0;
				
				node = myStank.pop();
				while(node != null){
					System.out.println("count[" + i + "]:" + node.getContent());
					i ++ ;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					node = myStank.pop();
				}
			}
			
		},"t4");
		
		t4.start();
		
	}

}
