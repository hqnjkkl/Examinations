package com.qunaer.examination;

/**
 * 命令解析 对于命令 -name jack -age 20 -address "HangZhou ZheDa Road" 要变成[-name jack,
 * -age 20, -address "HangZhou ZheDa Road"]
 * 命令由参数和值对组成，参数以-开头，参数和值之间，值和值之间都是用空格隔开，双引号“”之间的值当做一个 来处理。所有只包括英文字母，数字，减号和双引号
 * 
 * @author hqn
 * @description
 * @version
 * @update 2014-9-23 下午9:16:58
 */
public class CommandParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CommandParser cp = new CommandParser();
		String res = cp.parseCommand("-name jack -age 20 -address \"HangZhou ZheDa Road\"");
		System.out.println(res);
		 res = cp.parseCommand("  -name jack   -age 20 -address   \"HangZhou  ZheDa Road\"");
		System.out.println(res);
		res = cp.parseCommand("-name    jack -age 20      -address \"HangZhou ZheDa Road\"  ");
		System.out.println(res);
	}

	String parseCommand(String com) {
		StringBuilder sb = new StringBuilder();
		String arrs[] = com.split("-");
		sb.append("[");
		for(int i=0;i<arrs.length;i++)
		{	
			if(arrs[i]!=null && arrs[i].length()>0)
			{
				//出去首尾空格
				String tmp = arrs[i].trim();
				//有可能是有空格，所以还要判断一次
				if(!tmp.equals(""))
				{
					sb.append('-');
					for(int j=0;j<tmp.length();j++)
					{
						if(tmp.charAt(j)==' ')
						{
							sb.append(tmp.substring(0, j));
							sb.append(' ');
							//去掉中间的空格
							while(j<tmp.length() && tmp.charAt(j)==' ')
							{
								j++;
							}
							if(j<tmp.length())
							{
								sb.append(tmp.substring(j, tmp.length()));
							}
							break;
						}
					}
					//sb.append(tmp);
					if(i!=arrs.length-1)
					{
						sb.append(',');
						sb.append(' ');
					}else {
						sb.append(']');
					}
				}
			}
		}
		return sb.toString();
	}
}
