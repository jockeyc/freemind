package com.mubu2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ButtonHandler implements ActionListener
{
	/**
	 * @funtion:��ҳ����������ť�¼�����Ӧ�Լ�ҳ���л�
	 */
	JTextField searchFile;
	JTable table;
	DefaultTableModel model;
	JFrame jFrame;
	void setDefaultTableModel(DefaultTableModel model) {
		this.model = model;
	}
	
	void setTable(JTable table) {
		this.table = table;
	}
	
	void setSearchFile(JTextField searchFile) {
		this.searchFile = searchFile;
	}

	public void actionPerformed(ActionEvent e)
	{
		String source=e.getActionCommand();
		if(source.equals("button_create"))   //�½�һ���ļ�Ȼ���л�ҳ��
		{
			System.out.println("�½�һ���ļ�");
			
			//����һ����������ѡ��Ŀ¼���ڸ�Ŀ¼���½��ļ�
			JFileChooser jfc=new JFileChooser();
			File file = null;
			
	        if(jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
	            file=jfc.getSelectedFile();
	            
	            if(!file.exists()) {
	            	try {
						file.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	            
	            
	            //�õ��ļ����һ�θ��ĵ�ʱ��
		        Calendar cal = Calendar.getInstance();
		        long time = file.lastModified();
		        cal.setTimeInMillis(time);
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String lastTime = formatter.format(cal.getTime());
		        
		        //���ļ���Ϣд��data.txt��
				StringBuffer sb=new StringBuffer();
				sb.append(file.getName() + "\t" + lastTime + "\t" + file.getParent() +"\r\n");
				try {
					FileWriter fw=new FileWriter("src/data.txt",true);
					fw.append(sb.toString());
					countChange(1);  //�ļ�����+1
					fw.close();
					Vector<String> row = new Vector<String>();
					row.add(file.getName());
					row.add(lastTime);
					row.add(file.getParent());
					model.addRow(row);
					String path=file.getParent()+"\\"+file.getName();
					System.out.println(file.getParent());
					jFrame.setContentPane(FilePanel.getInstance(jFrame,path));
	                jFrame.validate();
				}catch(IOException ee)
				{
					ee.printStackTrace();
				}
				
	        }
	        else
	            System.out.println("No file is selected!");
	      
		}
		else if(source.equals("button_open"))  
			//���ı�ѡ������һ���Ѵ��ڵ��ļ�Ȼ���л�����
		{
			System.out.println("��һ���ļ�");
			/**
			 * *�����д�Ĵ���
			 */
			//����һ�����ڣ�ѡ���ļ�����
			JFileChooser jfc=new JFileChooser();
			
	        if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	            File file=jfc.getSelectedFile();
	            //ҳ����ת
	            String path=file.getPath();
	            jFrame.setContentPane(FilePanel.getInstance(jFrame,path));
                jFrame.validate();
                
	        }
	        else
	            System.out.println("No file is selected!");
	        
		}
		else if(source.equals("button_pages"))
			//����ĳһ��ָ�����Ƶ��ļ���Ȼ�����ļ��б���ѡ�н��
		{
			System.out.println("�����ļ�");
//			Item_file[] a=openFileDetail();
			/**
			 * *�����д�Ĵ���
			 */
			String filename = searchFile.getText();
			
			Scanner in = null;
			try {
				in = new Scanner(new File("src/data.txt"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String line = in.nextLine();
			int cnt = Integer.parseInt(line);
			int idx = -1, now =0;
			
			while(in.hasNext()) {
				line = in.nextLine();
				String [] item = line.split("\t");
				if(filename.equals(item[0])) {
					idx = now;
					break;
				}
				now++;
			}
			
			if(idx != -1) {
				table.setRowSelectionInterval(idx, idx);
			}
			
			in.close();
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
			File data=new File("src/data.txt");
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
			File data=new File("src/data.txt");
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
        	FileWriter fw=new FileWriter("src/data.txt");
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
	public void setjframe(JFrame jFrame) {
		// TODO �Զ����ɵķ������
		this.jFrame=jFrame;
	}
}
