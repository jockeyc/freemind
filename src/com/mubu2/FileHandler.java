package com.mubu2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileHandler {
	
	/*
	 * �������½�һ���ļ�
	 * ��������Ŀ¼���ļ���
	 */
	static void create(File parent,String fileName) {
		File file = new File(parent,fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
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
	static void delete(File parent,String fileName) {
		File file = new File(parent, fileName);
		if(file.exists()) {
			file.delete();
		}
	}
	
	/*
	 * ������������һ���Ѵ��ڵ��ļ�
	 * ��������Ŀ¼��ԭ�ļ��������ļ���
	 */
	static void rename(File parent,String fileName,String newFileName) {
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
	static void open(JFrame jFrame,File parent,String fileName) {
		File file = new File(parent,fileName);
		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, "�ļ�������", "�ļ�·������", JOptionPane.OK_OPTION);
		}else {
			jFrame.setContentPane(FilePanel.getInstance(jFrame,file.toString()));
            jFrame.validate();
		}
	}
}
