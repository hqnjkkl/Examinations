package com.wap.test1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class myQueue<E> implements ExamImmutableQueue<E> {

	private Object[] queue;
	private int size;
	
	
	public static void main(String[] args) {
		ExamImmutableQueue<Integer> q1 = new myQueue<Integer>();
		ExamImmutableQueue<Integer> q2 = new myQueue<Integer>();
		for(int i=1;i<11;i++)
		{
			q2 = q1.enqueue(i);
			System.out.println("enqueue:"+i+":"+q1.size()+"\t"+q2.size()+"\t"+q1.peek()+"\t"+q2.peek());
			q1 = q2;
		}
		for(int i=1;i<14;i++)
		{
			q2 = q1.dequeue();
			System.out.println("dequeue:"+i+":"+q1.size()+"\t"+q2.size()+"\t"+q1.peek()+"\t"+q2.peek());
			q1 = q2;
		}
	}
	
	private myQueue()
	{
		queue = new Object[0];
		size = 0;
	};
	
	private myQueue(E[] es)
	{
		if(es==null)
		{
			throw new NullPointerException("null");
		}
		queue = es;
		size = es.length;
	}
	@Override
	public ExamImmutableQueue<E> enqueue(E e) {
	//	Queue queue = new PriorityQueue<E>();
		E[] newQueue = (E[])new Object[size+1];
		System.arraycopy(queue, 0, newQueue, 0, size);
		newQueue[size] = e;
		myQueue<E> newMyQueue = new myQueue<E>(newQueue);
		return newMyQueue;
	}

	@Override
	public ExamImmutableQueue<E> dequeue() {
		if(size<=1)
		{
			return new myQueue<E>();
		}else
		{
			//Object[] newQueue = new Object[size-1];
			Object[] newQueue = Arrays.copyOfRange(queue, 1, size);
			myQueue<E> newMyQueue = new myQueue<E>((E[])newQueue);
			return newMyQueue;
		}
	}

	@Override
	public E peek() {
		if(size==0)
		{
			return null;
		}
		return (E) queue[0];
	}

	@Override
	public int size() {
		return size;
	}

}


