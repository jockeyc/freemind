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
		
		menuItem1.setText("打开");
		menuItem2.setText("新建");
		menuItem3.setText("删除");
		menuItem4.setText("重命名");
		
		//右键菜单打开
		menuItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("打开"+path+fileName);
				fileHandler.open(jFrame, new File(path), fileName);
			}
		});
		
		//右键菜单新建
		menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("新建");
				fileHandler.create(new File(path), fileName);
			}
		});
		
		//右键菜单删除
		menuItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("删除");
				fileHandler.delete(new File(path), fileName);
			}
		});
		
		//右键菜单重命名
		menuItem4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("重命名");
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
