package com.mubu2;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

public class RenameDialog extends JDialog{
	private JTextField textField;
	private JButton button_ok;
	FileHandler fileHandler = new FileHandler();
	DefaultTableModel model;
	int index;
	
	
	public RenameDialog(JFrame jFrame) {
		super(jFrame,true);
		setBounds(new Rectangle(150, 300, 300, 100));
		setTitle("\u91CD\u547D\u540D");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		getContentPane().add(textField, BorderLayout.CENTER);
		textField.setColumns(20);
		
		button_ok = new JButton("\u786E\u8BA4");
		button_ok.setBounds(new Rectangle(0, 0, 100, 20));
		getContentPane().add(button_ok, BorderLayout.SOUTH);
		button_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String newFileName = textField.getText();
			
				String path = model.getValueAt(index, 2).toString();
				String FileName = model.getValueAt(index, 0).toString();
				fileHandler.rename(new File(path), FileName, newFileName);
				RenameDialog.this.setVisible(false);
			}
		});
	}
	
	public void setModel(DefaultTableModel model) {
		this.model = model;
		fileHandler.setModel(model);
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
}
