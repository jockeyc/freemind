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
		
		menuItem1.setText("��");
		menuItem2.setText("�½�");
		menuItem3.setText("ɾ��");
		menuItem4.setText("������");
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
            main.jFrame.setContentPane(FilePanel.getInstance(main.jFrame,path));
            main.jFrame.validate();
            
        }
        else
            System.out.println("No file is selected!");
	}

	public void create() {
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
}
