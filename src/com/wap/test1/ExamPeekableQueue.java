package com.wap.test1;

public interface ExamPeekableQueue<E extends Comparable<E>> {

	public void enqueue(E e);
	public E dequeue();
	public E peekMedian();
	public E peekMaximum();
	public E peekMinimum();
	public int size();
	
}
