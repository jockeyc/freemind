package com.mubu2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class Data {
	int count;
	Vector<Item_file> files = new Vector<Item_file>();
	DefaultTableModel model = new DefaultTableModel();
	
	public void setModel(DefaultTableModel model) {
		this.model = model;
	}
	
	void init(String path) {
		files.removeAllElements();
		Scanner in = null;
		try {
			in = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count = Integer.parseInt(in.nextLine());
		while(in.hasNext()) {
			String info[] = in.nextLine().split("\t");
			Item_file item = new Item_file();
			item.changeName(info[0]);
			item.changeTime(info[1]);
			item.changePath(info[2]);
			files.add(item);
		}
	}
	
	void push(String path) {
		File file = new File(path);
		if(!file.exists()) return;
		
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			count =files.size();
			out.write("" + count);
			out.newLine();
			for(int i=0;i<files.size();i++) {
				Item_file item = files.get(i);
				String string = item.name + "\t" + item.time + "\t" + item.path + "\t";
				out.write(string);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Vector vec = new Vector();
		
		for(int i=0;i<files.size();i++) {
			Vector<String> tmp = new Vector<String>();
			tmp.removeAllElements();
			tmp.add(files.get(i).name);
			tmp.add(files.get(i).time);
			tmp.add(files.get(i).path);
			vec.add(tmp);
		}
		Vector<String> colName = new Vector<String>();
		colName.add("");
		colName.add("");
		colName.add("");
		model.setDataVector(vec, colName);

	}
	
	void push() {
		push("src/data.txt");
	}
	
	void add(Item_file item) {
		files.add(item);
	}
	
	void delete(Item_file item) {
		for(int i=0;i<files.size();i++) {
			if(files.get(i).equals(item)) {
				files.remove(i);
				i--;
			}
			
		}
	}
	void show() {
		for(int i=0;i<files.size();i++) {
			System.out.println(files.get(i).name + "\t" + files.get(i).time + "\t" +files.get(i).path);
		}
	}
	
	
	public Data(String path) {
		init(path);
	}
	
	public Data() {
		init("src/data.txt");
	}
}
