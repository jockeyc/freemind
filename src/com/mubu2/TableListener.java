package com.mubu2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

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
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2)
		{
			int count=jTable.getSelectedRow();
			Scanner in=null;
			try {
				in = new Scanner(new File("src/data.txt"));
			} catch (FileNotFoundException e1) {
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
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}
