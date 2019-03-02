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
	 * @funtion:主页面中三个按钮事件的相应以及页面切换
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
		if(source.equals("button_create"))   //新建一个文件然后切换页面
		{
			System.out.println("新建一个文件");
			
			//弹出一个窗口用于选中目录，在该目录下新建文件
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
	            
	            
	            //得到文件最后一次更改的时间
		        Calendar cal = Calendar.getInstance();
		        long time = file.lastModified();
		        cal.setTimeInMillis(time);
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String lastTime = formatter.format(cal.getTime());
		        
		        //将文件信息写入data.txt中
				StringBuffer sb=new StringBuffer();
				sb.append(file.getName() + "\t" + lastTime + "\t" + file.getParent() +"\r\n");
				try {
					FileWriter fw=new FileWriter("src/data.txt",true);
					fw.append(sb.toString());
					countChange(1);  //文件个数+1
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
			//用文本选择器打开一个已存在的文件然后切换界面
		{
			System.out.println("打开一个文件");
			/**
			 * *添加想写的代码
			 */
			//弹出一个窗口，选择文件并打开
			JFileChooser jfc=new JFileChooser();
			
	        if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
	            File file=jfc.getSelectedFile();
	            //页面跳转
	            String path=file.getPath();
	            jFrame.setContentPane(FilePanel.getInstance(jFrame,path));
                jFrame.validate();
                
	        }
	        else
	            System.out.println("No file is selected!");
	        
		}
		else if(source.equals("button_pages"))
			//查找某一个指定名称的文件，然后在文件列表中选中结果
		{
			System.out.println("查找文件");
//			Item_file[] a=openFileDetail();
			/**
			 * *添加想写的代码
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
			File data=new File("src/data.txt");
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
			File data=new File("src/data.txt");
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
		// TODO 自动生成的方法存根
		this.jFrame=jFrame;
	}
}
