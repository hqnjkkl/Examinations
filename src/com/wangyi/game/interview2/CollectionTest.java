package com.wangyi.game.interview2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CollectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		//添加单个数字
		list.add(3);
		list.add(0, 6);
		System.out.println(list);
		Queue<Integer> myQueue = new PriorityQueue<Integer>();
		myQueue.add(100);
		myQueue.add(101);
		//添加集合
		list.addAll(myQueue);
		System.out.println(list);
		//在某个地方加入整个集合
		list.addAll(1, myQueue);
		System.out.println(list.size());
		System.out.println(list);
		//修改某个位置的值
		list.set(3, 999);
		System.out.println(list);
		//集合变成数组
		Object[] arrs = list.toArray();
		System.out.println(arrs.length);
		for(int i=0;i<arrs.length;i++)
		{
			System.out.print(arrs[i]+",");
		}
		System.out.println();
		//是否包含某个数字
		System.out.println(list.contains(4));
		System.out.println(list.contains(999));
		//获得子链表
		List<Integer> subList = list.subList(0, 2);
		System.out.println(subList);
		//获得某个值的索引
		System.out.println(list.indexOf(0));
		//删除某个元素，根据元素的值来删除,只删除了第一个
		list.remove(new Integer(100));
		System.out.println(list);
		
		
		System.out.println();
	}
	
}
