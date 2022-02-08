package com.yimo.thread.threadSafe;

public class Mgr03 {
	private static Mgr03 INSTANCE;

	private Mgr03() {
	}

	public static Mgr03 getInstance(){
		synchronized(Mgr03.class){
			if(INSTANCE == null){
				try{
					Thread.sleep(1);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				INSTANCE = new Mgr03();
			}
		}
		return INSTANCE;
	}
	public void m(){System.out.println("m");}
	
	public static void main(String[] args){
		for(int i=0; i<100; i++){
			new Thread(new Runnable(){
				public void run() {
					System.out.println(Mgr03.getInstance().hashCode());
				}}
			).start();
		}
	}
	
}
