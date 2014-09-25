package com.wap.test1;

public interface ExamImmutableQueue<E> {

	ExamImmutableQueue<E> enqueue(E e);
	 
	ExamImmutableQueue<E> dequeue();
	
	E peek();
	
	int size();
	
}
