package com.mubu2;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Popup;

public class TableListener implements MouseListener {
	
	JFrame jFrame;
	JTable jTable;
	
	public void setJframe(JFrame jFrame) {
		this.jFrame = jFrame;
	}
	
	public void setJtable(JTable jTable) {
		this.jTable = jTable;
	}
	
	public TableListener(JFrame jFrame,JTable jTable) {
		this.jFrame = jFrame;
		this.jTable = jTable;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2)
		{
			int count=jTable.getSelectedRow();
			System.out.println(count);
			/*
			 * 添加想写的代码
			 */
			Scanner in=null;
			try {
				in = new Scanner(new File("src/data.txt"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int tot = -1;
			if(in.hasNext()) {
				tot = Integer.parseInt(in.nextLine());
			}
			int idx = 0;
			String string = "";
			while(idx<tot&&idx<count&&in.hasNext())
			{
				string = in.nextLine();
				idx++;
			}
			if(idx == count) {
				String s[] = string.split("\t");
				String filepath = s[2]; 
				File check = new File(filepath);
				if(!check.exists())
				{
					JOptionPane.showMessageDialog(null, "该文件不存在", "文件路径错误", JOptionPane.ERROR_MESSAGE);
				}else {
					jFrame.setContentPane(FilePanel.getInstance(jFrame,filepath));
					jFrame.validate();
				}
				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
