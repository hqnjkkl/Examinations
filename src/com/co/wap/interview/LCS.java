package com.co.wap.interview;


public class LCS {

	/**
	 * @param args
	 */
	int dp[][];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LCS fun = new LCS();
		String res = fun.lcs("abcdfggggbb", "cabcdfgb");
		System.out.println(res);
	}
	
	public String lcs(String s1,String s2)
	{
		int len1 = s1.length(),len2 = s2.length();
		dp = new int[len1+1][len2+1];
		int i,j,maxLen,maxIndex,tmpLen;
		if(s1.length()==0||s2.length()==0)
		{
			return null;
		}
		for(i=0;i<len1+1;i++)
		{
			dp[i][0] = 0;
		}
		for(j=0;j<len2+1;j++)
		{
			dp[0][j] = 0;
		}
		maxLen = 0;
		maxIndex = 0;
		for(i=0;i<len1;i++)
		{
			for(j=0;j<len2;j++)
			{
				if(s1.charAt(i)==s2.charAt(j))
				{
					dp[i+1][j+1] = dp[i][j] +1;
				}else
				{
					dp[i+1][j+1] = 0;
				}
				if(dp[i+1][j+1]>maxLen)
				{
					maxLen = dp[i+1][j+1];
					maxIndex = i;
				}
			}
		}
		return s1.substring(maxIndex-maxLen+1,maxIndex+1);
	}
}
