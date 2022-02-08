package com.yimo.list;

public class MyNode {
	
	private MyNode next;
	private String content;
	
	public MyNode(String s){
		setContent(s);
	}

	public MyNode getNext() {
		return next;
	}

	public void setNext(MyNode next) {
		this.next = next;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
