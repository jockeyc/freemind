package com.mubu2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

public class TimerHandler implements ActionListener {
	JLabel jLabel = new JLabel();
	
	public void setjLabel(JLabel jLabel) {
		this.jLabel = jLabel;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String text = "当前北京时间：";
		long time = System.currentTimeMillis();
		SimpleDateFormat clock = new SimpleDateFormat("HH:mm:ss");
		text += clock.format(new Date(time));
		jLabel.setText(text);
	}
}
