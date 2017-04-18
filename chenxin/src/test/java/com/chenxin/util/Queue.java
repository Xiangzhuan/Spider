package com.chenxin.util;

import java.util.LinkedList;

/**
 * 队列，保存将要访问的url
 * @author j
 *
 */
public class Queue {
	/**
	 * 定义一个队列，使用linkedList实现
	 */
	private LinkedList<Object> queue = new LinkedList<Object>();
	
	//入队
	public void enQueue(Object t){
		queue.addLast(t);
	}
	
	//出队，并返回
	public Object deQueue(){
		return queue.removeFirst();
	}
	
	//返回队列是否为空
	public boolean isQueueEmpty(){
		return queue.isEmpty();
	}
	
	//返回并判断队列是否包含t
	public boolean contains(Object t){
		return queue.contains(t);
	}
	
	//判断并返回队列是否为空
	public boolean empty(){
		return queue.isEmpty();
	}
	

}
