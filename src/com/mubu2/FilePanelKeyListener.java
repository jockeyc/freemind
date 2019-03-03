package com.mubu2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

public class FilePanelKeyListener implements KeyListener{
	JTextArea textField;
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==8) {
			String text = textField.getText();
			if(text.length()==1) {
				text+="¡ğ";
			}
			textField.setText(text);
			textField.setCaretPosition(1);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("»Ø³µ");
			int pos = textField.getCaretPosition()-1;
			System.out.println("pos:"+pos);
			int count_tab=0;
			String text = textField.getText();
			int start=pos,end=pos;
			while(start > 0 && text.charAt(start-1)!='\t'&&text.charAt(start-1)!='\n') start--;
			while(start > 0 && text.charAt(start-1)!='\n') {
				start--;
				count_tab++;
			}
			System.out.println("count:"+count_tab);
			while(end < text.length() && text.charAt(end) != '\n') end++;
			System.out.println("pos"+pos+" "+"start"+start);
			StringBuilder sb = new StringBuilder(text);
			for (int i = 0; i < count_tab; i++) {
				try {
					sb.insert(++end, "\t");
				}
				catch (Exception e1) {
					// TODO: handle exception
				}	
			}
			sb.insert(++end, "¡ğ");
			text = sb.toString();
			textField.setText(text);
			textField.setCaretPosition(end+1);
		}
		if(e.getKeyCode()==8) {
			textField.setCaretPosition(1);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setTextField(JTextArea textField) {
		this.textField = textField;
	}
}
