package com.mubu2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * ���������ڶ��ļ�����ɾ��д�Լ�ˢ��table
 * �÷�������
 * FileHandler filehandler = new FileHandler();
 * fileHandler.setModel(model);
 * fileHandler.add(parent,fileName);
 * fileHandler.delete(parent,fileName);
 * fileHandler.rename(parent,fileName,newfileName);
 * fileHandler.open(jFrame,path);
 */

public class FileHandler {
	DefaultTableModel model;
	Data data;
	
	public FileHandler() {
		// TODO Auto-generated constructor stub
		data = new Data();
	}
	
	public void setModel(DefaultTableModel model) {
		this.model = model;
		data.setModel(model);
	}
	
	/*
	 * �������½�һ���ļ�
	 * ��������Ŀ¼���ļ���
	 */
	void create(File parent,String fileName) {
		File file = new File(parent,fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
				Item_file item = getItem(file);
		        
		        data.add(item);
		        data.push();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "���ļ��Ѿ�����", "�ļ�����ͻ", JOptionPane.OK_OPTION);
		}
	}
	
	/*
	 * ������ɾ��һ���ļ�
	 * ��������Ŀ¼���ļ���
	 */
	void delete(File parent,String fileName) {
		File file = new File(parent, fileName);
		Item_file item = getItem(file);
		data.delete(item);
		data.push();
		if(file.exists()) {
			file.delete();
		}
	}
	
	/*
	 * ������������һ���Ѵ��ڵ��ļ�
	 * ��������Ŀ¼��ԭ�ļ��������ļ���
	 */
	void rename(File parent,String fileName,String newFileName) {
		File file = new File(parent,fileName);
		
		if(file.exists()) {
			if(newFileName.equals("")) {
				return;
			}else {
				File newFile = new File(parent,newFileName);
				if(newFile.exists()) {
					JOptionPane.showMessageDialog(null, "���ļ��Ѵ���", "�ļ�����ͻ", JOptionPane.OK_OPTION);
					return;
				}else {
					data.delete(getItem(file));
					data.add(getItem(newFile));
					data.push();
					file.renameTo(newFile);
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "Դ�ļ�������", "�ļ�·������", JOptionPane.OK_OPTION);
		}
	}
	
	/*
	 * ��������һ���ļ�������FilePanel��չʾ
	 * ������������ʾ�����JFrame����Ŀ¼���ļ���
	 */
	void open(JFrame jFrame,File parent,String fileName) {
		File file = new File(parent,fileName);
		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, "�ļ�������", "�ļ�·������", JOptionPane.OK_OPTION);
		}else {
			jFrame.setContentPane(FilePanel.getInstance(jFrame,file.toString()));
            jFrame.validate();
		}
	}
	
	/*
	 * �������õ�һ��File��Item_file��Ϣ
	 * ������File
	 */
	Item_file getItem(File file) {
		Item_file item = new Item_file();
		
		Calendar cal = Calendar.getInstance();
        long time = file.lastModified();
        cal.setTimeInMillis(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastTime = formatter.format(cal.getTime());
        
        item.changeName(file.getName());
        item.changePath(file.getParent());
        item.changeTime(lastTime);
        
        return item;
	}
}
