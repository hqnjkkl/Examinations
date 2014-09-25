package com.wap.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

public class myPeekableQueue<E extends Comparable<E>> implements
		ExamPeekableQueue<E> {

	private LinkedList<E> stack1;
	private LinkedList<E> stack2;
	private LinkedList<E> minValue1;
	private LinkedList<E> minValue2;
	private LinkedList<E> maxValue1;
	private LinkedList<E> maxValue2;
	
	public static void main(String[] args) {
		myPeekableQueue<Integer> mp1 = new myPeekableQueue<Integer>();
		int[] test = {3,8,7,2,10,1,0,20,30,11}; 
		for(int i=0;i<test.length;i++)
		{
			
			mp1.enqueue(test[i]);
			System.out.println("en"+mp1.size()+"\t"+mp1.peekMaximum()+"\t"+mp1.peekMinimum()+
					"\t"+mp1.peekMedian()+"\t");
		}
		for(int i=0;i<20;i++)
		{
			mp1.dequeue();
			System.out.println("de:"+mp1.size()+"\t"+mp1.peekMaximum()+"\t"+mp1.peekMinimum()+
					"\t"+mp1.peekMedian()+"\t");
		}
	}
	
	public myPeekableQueue()
	{
		stack1 = new LinkedList<E>();
		stack2 = new LinkedList<E>();
		minValue1 = new LinkedList<E>();
		minValue2 = new LinkedList<E>();
		maxValue1 = new LinkedList<E>();
		maxValue2 = new LinkedList<E>();
	}
	@Override
	public void enqueue(E e) {
		if(e==null)
			throw new NullPointerException();
		stack1.push(e);
		pushValue1(e);
		return ;
	}
	private void pushValue1(E e)
	{
		
		if(minValue1.size()==0)
		{
			minValue1.push(e);
		}else
		{
			if(e.compareTo(minValue1.peek())<0)
			{
				minValue1.push(e);
			}else
			{
				minValue1.push(minValue1.peek());
			}
		}
		
		if(maxValue1.size()==0)
		{
			maxValue1.push(e);
		}else
		{
			if(e.compareTo(maxValue1.peek())>0)
			{
				maxValue1.push(e);
			}else
			{
				maxValue1.push(maxValue1.peek());
			}
		}
	}
	
	/**
	 * set the max value and min value of stack2
	 * @param e the element
	 */
	private void pushValue2(E e)
	{
		if(minValue2.size()==0)
		{
			minValue2.push(e);
		}else
		{
			if(e.compareTo(minValue2.peek())<0)
			{
				minValue2.push(e);
			}else
			{
				minValue2.push(minValue2.peek());
			}
		}
		if(maxValue2.size()==0)
		{
			maxValue2.push(e);
		}else
		{
			if(e.compareTo(maxValue2.peek())>0)
			{
				maxValue2.push(e);
			}else
			{
				maxValue2.push(maxValue2.peek());
			}
		}
		return ;
	}

	@Override
	public E dequeue() {
		if(stack1.size()==0&&stack2.size()==0)
		{
			return null;
		}else
		{
			if(stack2.size()!=0)
			{
				E e = stack2.pop();
				minValue2.pop();
				maxValue2.pop();
				return e;
			}else
			{
				while(stack1.size()>1)
				{
					E e = stack1.pop();
					minValue1.pop();
					maxValue1.pop();
					stack2.push(e);
					pushValue2(e);
				}
				return stack1.pop();
			}
		}
	}

	@Override
	public E peekMedian() {
		int size1 = stack1.size();
		int size2 = stack2.size();
		
		if(size()==0)
			return null;
		//E[] es1 = (E[]) stack1.toArray();
		List<E> sortList = new ArrayList<E>(stack1);
		sortList.addAll(stack2);
		
	//	E[] es1 = (E[])stack1.toArray();
		//E[] es2 = (E[])stack2.toArray();
		//System.arraycopy(es1, 0, sortArray, 0, size1);
		//System.arraycopy(es2, 0, sortArray, size1, size2);
		//Arrays.sort(sortArray);
		Collections.sort(sortList);
		return sortList.get(size()/2);
	}

	@Override
	public E peekMaximum() {
		if(size()==0)
		{
			return null;
		}else
		{
			if(stack1.size()>0&&stack2.size()>0)
			{
				E e1 = maxValue1.peek();
				E e2 = maxValue2.peek();
				if(e1.compareTo(e2)>0)
				{
					return e1;
				}else
				{
					return e2;
				}
			}
			if(stack2.size()>0)
			{
				return maxValue2.peek();
			}else
			{
				return maxValue1.peek();
			}	
		}
	}

	@Override
	public E peekMinimum() {
		if(size()==0)
		{
			return null;
		}else
		{
			if(stack1.size()>0&&stack2.size()>0)
			{
				E e1 = minValue1.peek();
				E e2 = minValue2.peek();
				if(e1.compareTo(e2)<0)
				{
					return e1;
				}else
				{
					return e2;
				}
			}
			if(stack1.size()>0)
			{
				return minValue1.peek();
			}else
			{
				return minValue2.peek();
			}	
		}
	}

	@Override
	public int size() {
		return stack1.size()+stack2.size();
	}
	
}
