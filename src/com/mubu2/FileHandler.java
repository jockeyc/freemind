package com.mubu2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileHandler {
	
	/*
	 * 描述：新建一个文件
	 * 参数：父目录、文件名
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
			JOptionPane.showMessageDialog(null, "该文件已经存在", "文件名冲突", JOptionPane.OK_OPTION);
		}
	}
	
	/*
	 * 描述：删除一个文件
	 * 参数：父目录、文件名
	 */
	static void delete(File parent,String fileName) {
		File file = new File(parent, fileName);
		if(file.exists()) {
			file.delete();
		}
	}
	
	/*
	 * 描述：重命名一个已存在的文件
	 * 参数：父目录、原文件名、新文件名
	 */
	static void rename(File parent,String fileName,String newFileName) {
		File file = new File(parent,fileName);
		
		if(file.exists()) {
			if(newFileName.equals("")) {
				return;
			}else {
				File newFile = new File(parent,newFileName);
				if(newFile.exists()) {
					JOptionPane.showMessageDialog(null, "该文件已存在", "文件名冲突", JOptionPane.OK_OPTION);
					return;
				}else {
					file.renameTo(newFile);
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "源文件不存在", "文件路径错误", JOptionPane.OK_OPTION);
		}
	}
	
	/*
	 * 描述：打开一个文件，并在FilePanel里展示
	 * 参数：程序显示所需的JFrame、父目录、文件名
	 */
	static void open(JFrame jFrame,File parent,String fileName) {
		File file = new File(parent,fileName);
		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, "文件不存在", "文件路径错误", JOptionPane.OK_OPTION);
		}else {
			jFrame.setContentPane(FilePanel.getInstance(jFrame,file.toString()));
            jFrame.validate();
		}
	}
}
