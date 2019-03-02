package com.mubu2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MyPopupMenu extends JPopupMenu{
	JMenuItem menuItem1 = new JMenuItem();
	JMenuItem menuItem2 = new JMenuItem();
	JMenuItem menuItem3 = new JMenuItem();
	JMenuItem menuItem4 = new JMenuItem();
	JTable table;
	DefaultTableModel model;
	JTextField searchFile;
	JFrame jFrame;
	FileHandler fileHandler = new FileHandler();
	int index;
	String path;
	String fileName;
	String newFileName;
	
	
	public MyPopupMenu() {
		// TODO Auto-generated constructor stub
		initMenuItem();
		
	}
	
	private void initMenuItem() {
		// TODO Auto-generated method stub
		Font font=new Font("����",Font.PLAIN,15);
		menuItem1.setText("����");
		menuItem1.setFont(font);
		menuItem1.setBackground(Color.WHITE);
		menuItem2.setText("���½�");
		menuItem2.setBackground(Color.WHITE);
		menuItem2.setFont(font);
		menuItem3.setText("��ɾ��");
		menuItem3.setFont(font);
		menuItem3.setBackground(Color.WHITE);
		menuItem4.setText("��������");
		menuItem4.setFont(font);
		menuItem4.setBackground(Color.WHITE);
		//�Ҽ��˵���
		menuItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("��"+path+fileName);
				fileHandler.open(jFrame, new File(path), fileName);
			}
		});
		
		//�Ҽ��˵��½�
		menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("�½�");
				fileHandler.create(new File(path), fileName);
			}
		});
		
		//�Ҽ��˵�ɾ��
		menuItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ɾ��");
				fileHandler.delete(new File(path), fileName);
			}
		});
		
		//�Ҽ��˵�������
		menuItem4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("������");
				RenameDialog dialog = new RenameDialog(jFrame);
				dialog.setIndex(index);
				dialog.setModel(model);
				dialog.setVisible(true);
				
			}
		});
		
		
		add(menuItem1);
		add(menuItem2);
		add(menuItem3);
		add(menuItem4);
//		Component
		this.setBackground(Color.WHITE);
		
	}
	
	public void setTable(JTable table) {
		this.table = table;
	}
	
	public void setSearchFile(JTextField searchFile) {
		this.searchFile = searchFile;
	}
	
	public void setjFrame(JFrame jFrame) {
		this.jFrame = jFrame;
	}
	
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	
	public void init() {
		fileHandler.setModel(model);
		index = table.getSelectedRow(); 
		path = model.getValueAt(index, 2).toString();
		fileName = model.getValueAt(index, 0).toString();
	}
	
}
