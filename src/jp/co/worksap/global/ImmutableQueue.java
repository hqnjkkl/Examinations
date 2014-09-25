package jp.co.worksap.global;

import java.util.NoSuchElementException;

/**
 * The Queue class represents an immutable first-in-first-out(FIFO) queue of objects.
 * @author hqn
 * @description  There are many methods to implement a immutable FIFO. But make faster execution
 * as the most important thing. We can use two stacks to simulate a FIFO queue. I think stack 
 * has very good immutability. As the elements push in, create a new stack. The new stack's head 
 * is the new element, and it's remaining element just point to the old stack.And remove 
 * elements is move it's head and tail pointer.
 *     We know that two stacks can immutate a FIFO queue. We call it stack a and stack b. Stack 
 * a is special for push elements. And stack b is special for pop elements and peek. 
 * If stack b is empty when poping a element, pop all the element in stack a and push it in stack
 * b one by one. At most time, time complexity of push and pop is just O(1). 
 * At some worst case time complexity
 * of pop is O(n). For example, after pushing n elements, and the b stack is empty,
 *  then the following pop or peek need O(n). But n-1  following pop is just O(1).
 * 
 * @version
 * @update 2014-5-29 afternoon 2:32:11 
 * @param <E>
 */
public class ImmutableQueue<E> {

	
	private class ImmutableStack<E>
	{
		private E head;
		private ImmutableStack<E> tail;
		int size;
		public ImmutableStack() {
			
		}
		/**
		 * make a new ImmutableStack
		 * @param e
		 */
		public ImmutableStack<E> push(E e)
		{
			ImmutableStack<E> newStack = new ImmutableStack<E>();
			newStack.head = e;
			newStack.tail = this;
			newStack.size = this.size+1;
			return newStack;
		}
		
		/**
		 * Make a new ImmutableStack whose head is original stack's head of tail, and 
		 * it's tail is original stack's tail's tail
		 * @return
		 */
		public ImmutableStack<E> pop()
		{
			
			ImmutableStack<E> newStack = new ImmutableStack<E>();
			//if the size is zero, the the tail of this is null,must remember
			if(this.size==1)
				return newStack;
			newStack.head = this.tail.head;
			newStack.tail = this.tail.tail;
			newStack.size = this.size - 1;
			return newStack;
		}
		
		/**
		 * Reverse this stack, and get s reversed stack
		 * @return
		 */
		public ImmutableStack<E> reverse()
		{
			
			ImmutableStack<E> newStack = new ImmutableStack<E>();
			if(this.size==0)
			{
				return newStack;
			}
			ImmutableStack<E> t = this.tail;
			
			newStack.head = this.head;
			newStack.size = 1;
			while(t.size>0)
			{
				newStack = newStack.push(t.head);
				t = t.tail;
			}
			return newStack;
		}
	}
	
	private ImmutableStack<E> pushStack;
	private ImmutableStack<E> popStack;
	
	
	public ImmutableQueue()
	{
		this.pushStack = new ImmutableStack<E>();
		this.popStack = new ImmutableStack<E>();
		
		//modify this constructor if necessary, but do not remove default constructor
		
	}

	
	//add other constructors if necessary
	
	/**
	 * 
	 * @param e
	 * @return
	 * @throws IllegalArgumentException
	 */
	public ImmutableQueue<E> enqueue(E e)
	{
		if(e==null)
		{
			throw new IllegalArgumentException("element is null");
		}
		ImmutableQueue<E> iQueue = new ImmutableQueue<E>();
		// ******* the first element should be pushed in the popstack
		if(size()==0)
		{
			//the first element should in popStack;
			iQueue.popStack = this.popStack.push(e);
			return iQueue;
		}
		
		iQueue.pushStack = this.pushStack.push(e);
		iQueue.popStack = this.popStack;
		return iQueue;
	}
	
	/**
	 * 
	 * @return
	 * @throws java.util.NoSuchElementException
	 */
	public ImmutableQueue<E> dequeue()
	{
		if(size()==0)
		{
			throw new NoSuchElementException("queue is empty");
		}
		ImmutableQueue<E> iQueue = new ImmutableQueue<E>();
		
		if(this.popStack.size==0)
		{
			iQueue.popStack = this.pushStack.reverse();
			// must add a pop method, so the elements will decrease;
			iQueue.popStack = iQueue.popStack.pop();
			return iQueue;
			
		// ***********the popstack has at least one element
		}else if(this.popStack.size==1)
		{
			if(this.pushStack.size>0)
			{				
				iQueue.popStack = this.pushStack.reverse();
			}
			return iQueue;
		}
		iQueue.popStack = this.popStack.pop();
		iQueue.pushStack = this.pushStack;
		return iQueue;
	}
	
	/**
	 * 
	 * @return
	 * @throws java.util.NoSuchElementException
	 */
	public E peek()
	{
		if(size()==0)
		{
			throw new NoSuchElementException("queue is empty");
		}
//		if(this.popStack.size==0)
//		{
//			this.popStack = pushStack.reverse();
//			this.pushStack = new ImmutableStack<E>();
//		}
		return popStack.head;
	}
	
	public int size()
	{
		return pushStack.size + popStack.size;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ImmutableQueue<Integer> iiq = new ImmutableQueue<Integer>();
		//iiq.testCorrect();
		return ;
	}
	
/*
	 //test whether this queue is correct
	public void testCorrect()
	{
		ImmutableQueue<Integer> iq = new ImmutableQueue<Integer>();
		int i = 0;
		for(i=0;i<30;i++)
		{
			iq = iq.enqueue(i);
			if((i+1)%10==0)
			{
				iq = iq.dequeue();
			}
			if(iq.size()>0)
			System.out.println("first push:i+1:"+(i+1)+"--"+iq.peek());
		}
		i = 0;
		while(iq.size()>0)
		{
			i++;
			System.out.println("second push:i+1:"+(i+1)+"--"+iq.peek());
			iq = iq.dequeue();
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		int random = 0;
		for(i=0;i<100;i++)
		{
			random = (int) (Math.random()*100);
			if(random%3!=0)
			{
				iq = iq.enqueue(random);
				queue.add(random);
			}else
			{
				if(iq.size()>0)
				{
				iq = iq.dequeue();
				queue.poll();
				}
			}
			
			if(iq.size()>0)
			System.out.println("third push:i+1:"+(i+1)+"--"+iq.peek()+
					"-"+iq.size()+"-quque:"+queue.peek()+","+queue.size());
			
			if(iq.size()!=queue.size())
			{
				if(iq.size()>=0)
					System.out.println("final push:i+1:"+(i+1)+"--"+
							"-"+iq.size()+"-quque:"+","+queue.size());
				throw new NullPointerException(iq.size()+"aa"+queue.size());
			}
			
			if(iq.size()>0)
			{
			System.out.println("fourth push:i+1:"+(i+1)+"--"+iq.peek()+"-"+iq.size()+"-quque:"+queue.peek()+","+queue.size());
			
			if(iq.peek()!=queue.peek())
			{
				throw new NullPointerException("aa");
			}
			
			}else
			{
				System.out.println("<0");
			}
			}
		i = 0;
		while(iq.size()>0)
		{
			i++;
			System.out.println("fifth push i+1:"+(i+1)+"--"+iq.peek()+"-"+iq.size()+"-quque:"+queue.peek()+","+queue.size());
			iq = iq.dequeue();
			queue.poll();
		}
	}

*/
}
