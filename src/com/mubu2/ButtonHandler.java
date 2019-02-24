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
	 * @funtion:��ҳ����������ť�¼�����Ӧ�Լ�ҳ���л�
	 */
	public void actionPerformed(ActionEvent e)
	{
		String source=e.getActionCommand();
		if(source.equals("button_create"))   //�½�һ���ļ�Ȼ���л�ҳ��
		{
			System.out.println("�½�һ���ļ�");
			StringBuffer sb=new StringBuffer();
			sb.append("δ����\t����ʱ��\tδ֪·��\r\n");
			try {
				FileWriter fw=new FileWriter("I:\\Java\\data.txt",true);
				fw.append(sb.toString());
				countChange(1);  //�ļ�����+1
				fw.close();
			}catch(IOException ee)
			{
				ee.printStackTrace();
			}
		}
		else if(source.equals("button_open"))  
			//���ı�ѡ������һ���Ѵ��ڵ��ļ�Ȼ���л�����
		{
			System.out.println("��һ���ļ�");
			/**
			 * *�����д�Ĵ���
			 */
		}
		else if(source.equals("button_pages"))
			//����ĳһ��ָ�����Ƶ��ļ���Ȼ�����ļ��б���ѡ�н��
		{
			System.out.println("�����ļ�");
//			Item_file[] a=openFileDetail();
			/**
			 * *�����д�Ĵ���
			 */
		}
//		else if(e.getSource()==table)   
		//˫���ļ��б��л�ҳ�棬����ʱ��֪��Ӧ��������д�ⲿ�ִ��룬��������뷨������Ⱥ����˵һ��
//		{
//			int count=table.getSelectedRow();
//			String getname=table.getValueAt(count, 1).toString();
//			System.out.println(getname);
//		}
	}
	Item_file[] openFileDetail() {       //��ȡ��¼�ļ��е����ݣ������ļ��б����ʾ
		// TODO �Զ����ɵķ������
		int count,i=0;
		Item_file[] item_file = null;
		try 
		{
			File data=new File("I:\\Java\\data.txt");
			InputStreamReader reader = new InputStreamReader(  
                    new FileInputStream(data)); // ����һ������������reader  
            BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
            String line = "";  
            line = br.readLine();
            count=Integer.parseInt(line);
            item_file=new  Item_file[count];
            line=br.readLine();
            while (line != null && i<count) 
            {
            	String s[]=line.split("\t");
                line = br.readLine(); // һ�ζ���һ������
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
	void countChange(int index)     //�Լ�¼�ļ�data.txt���ļ������ĸ���
	{
		try {
			File data=new File("I:\\Java\\data.txt");
			InputStreamReader reader = new InputStreamReader(new FileInputStream(data)); // ����һ������������reader  
        	BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
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
