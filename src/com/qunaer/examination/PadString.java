package com.qunaer.examination;
/**
 *   1，String padString(String string,int minLength,char padChar);
    就是在string前用padChar把string填充到至少minLength长度
    如("7",3'0'),得到的结果是"007";
    如("2012",3,'0'),的到的结果是"2012";
    
 * @author hqn
 * @description   
 * @version
 * @update 2014-9-23 下午9:07:30
 */
public class PadString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PadString ps = new PadString();
		String res = ps.padString("2012", 3, '0');
		System.out.println(res);
	}
	
	String padString(String string,int minLength,char padChar)
	{
		StringBuilder res = new StringBuilder();// StringBuilder比StringBuffer更快，但是StringBuilder线程非安全
		if(minLength<string.length())
		{
			return string;
		}
		int padLength = minLength - string.length();
		for(int i=0;i<padLength;i++)
		{
			res.append(padChar);
		}
		res.append(string);
		return res.toString();
	}

}
