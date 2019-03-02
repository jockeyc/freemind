package com.mubu2;

import java.awt.MenuItem;
import java.awt.PopupMenu;
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
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MyPopupMenu extends JPopupMenu{
	JMenuItem menuItem1 = new JMenuItem();
	JMenuItem menuItem2 = new JMenuItem();
	JMenuItem menuItem3 = new JMenuItem();
	JMenuItem menuItem4 = new JMenuItem();
	Main main;
	public MyPopupMenu(Main main) {
		// TODO Auto-generated constructor stub
		this.main=main;
		initMenuItem();
	}
	
	private void initMenuItem() {
		// TODO Auto-generated method stub
		
		menuItem1.setText("打开");
		menuItem2.setText("新建");
		menuItem3.setText("删除");
		menuItem4.setText("重命名");
		menuItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				open();
			}
		});
		menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				create();
			}
		});
		add(menuItem1);
		add(menuItem2);
		add(menuItem3);
		add(menuItem4);
		
	}
	
	protected void open() {
		// TODO Auto-generated method stub
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
            main.jFrame.setContentPane(FilePanel.getInstance(main.jFrame,path));
            main.jFrame.validate();
            
        }
        else
            System.out.println("No file is selected!");
	}

	public void create() {
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
				main.model.addRow(row);
				String path=file.getParent()+"\\"+file.getName();
				System.out.println(file.getParent());
				main.jFrame.setContentPane(FilePanel.getInstance(main.jFrame,path));
                main.jFrame.validate();
			}catch(IOException ee)
			{
				ee.printStackTrace();
			}
			
        }
        else
            System.out.println("No file is selected!");
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
}
