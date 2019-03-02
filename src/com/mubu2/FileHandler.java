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
 * 描述：用于对文件的增删改写以及刷新table
 * 用法举例：
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
	 * 描述：新建一个文件
	 * 参数：父目录、文件名
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
			JOptionPane.showMessageDialog(null, "该文件已经存在", "文件名冲突", JOptionPane.OK_OPTION);
		}
	}
	
	/*
	 * 描述：删除一个文件
	 * 参数：父目录、文件名
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
	 * 描述：重命名一个已存在的文件
	 * 参数：父目录、原文件名、新文件名
	 */
	void rename(File parent,String fileName,String newFileName) {
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
					data.delete(getItem(file));
					data.add(getItem(newFile));
					data.push();
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
	void open(JFrame jFrame,File parent,String fileName) {
		File file = new File(parent,fileName);
		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, "文件不存在", "文件路径错误", JOptionPane.OK_OPTION);
		}else {
			jFrame.setContentPane(FilePanel.getInstance(jFrame,file.toString()));
            jFrame.validate();
		}
	}
	
	/*
	 * 描述：得到一个File的Item_file信息
	 * 参数：File
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
