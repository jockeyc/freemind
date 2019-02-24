package com.mubu2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ButtonHandler implements ActionListener
{
	/**
	 * @funtion:主页面中三个按钮事件的相应以及页面切换
	 */
	public void actionPerformed(ActionEvent e)
	{
		String source=e.getActionCommand();
		if(source.equals("button_create"))   //新建一个文件然后切换页面
		{
			System.out.println("新建一个文件");
			StringBuffer sb=new StringBuffer();
			sb.append("未命名\t创建时间\t未知路径\r\n");
			try {
				FileWriter fw=new FileWriter("I:\\Java\\data.txt",true);
				fw.append(sb.toString());
				countChange(1);  //文件个数+1
				fw.close();
			}catch(IOException ee)
			{
				ee.printStackTrace();
			}
		}
		else if(source.equals("button_open"))  
			//用文本选择器打开一个已存在的文件然后切换界面
		{
			System.out.println("打开一个文件");
			/**
			 * *添加想写的代码
			 */
		}
		else if(source.equals("button_pages"))
			//查找某一个指定名称的文件，然后在文件列表中选中结果
		{
			System.out.println("查找文件");
//			Item_file[] a=openFileDetail();
			/**
			 * *添加想写的代码
			 */
		}
//		else if(e.getSource()==table)   
		//双击文件列表切换页面，我暂时不知道应该在哪里写这部分代码，如果你有想法可以在群里面说一下
//		{
//			int count=table.getSelectedRow();
//			String getname=table.getValueAt(count, 1).toString();
//			System.out.println(getname);
//		}
	}
	Item_file[] openFileDetail() {       //读取记录文件中的内容，用于文件列表的显示
		// TODO 自动生成的方法存根
		int count,i=0;
		Item_file[] item_file = null;
		try 
		{
			File data=new File("I:\\Java\\data.txt");
			InputStreamReader reader = new InputStreamReader(  
                    new FileInputStream(data)); // 建立一个输入流对象reader  
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
            String line = "";  
            line = br.readLine();
            count=Integer.parseInt(line);
            item_file=new  Item_file[count];
            line=br.readLine();
            while (line != null && i<count) 
            {
            	String s[]=line.split("\t");
                line = br.readLine(); // 一次读入一行数据
                item_file[i]=new Item_file(s[0],s[1],s[2]);
                i++;
            }
            br.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return item_file;
	}
	void countChange(int index)     //对记录文件data.txt中文件个数的更改
	{
		try {
			File data=new File("I:\\Java\\data.txt");
			InputStreamReader reader = new InputStreamReader(new FileInputStream(data)); // 建立一个输入流对象reader  
        	BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
        	ArrayList<String> list=new ArrayList<String>();
        	String line=br.readLine();
        	while(line!=null)
        	{
        		list.add(line);
        		line=br.readLine();
        	}
        	data.createNewFile();
        	FileWriter fw=new FileWriter("I:\\Java\\data.txt");
        	String a=list.get(0);
        	int b=Integer.parseInt(a)+index;
        	fw.append(String.valueOf(b)).append("\r\n");
        	for(int i=1;i<list.size();i++)
        	{
        		fw.append(list.get(i)).append("\r\n");
        	}
        	fw.close();
        	br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
