package com.yimo.thread.threadSafe;

public class Launcher {
	private static Launcher launcher;
	
//	public Launcher(){
//		launcher = new Launcher();
//	}
	
	public static Launcher getInstance(){
		if(launcher == null){
			launcher = new Launcher();
		}
		return launcher;
	}
	
	public Launcher getLauncher(){
		return launcher;
	}
	
	public void sayHello(){
		System.out.println("Hello!");
	}
	
	public static void main(String[] args){
//		Launcher l = new Launcher();
//		Launcher l1 = l.getInstance().getInstance().getInstance().getInstance();
//		l1.sayHello();
		
		Launcher.getInstance().sayHello();
	}
	
}
