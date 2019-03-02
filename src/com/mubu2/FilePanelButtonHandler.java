package com.mubu2;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

public class FilePanelButtonHandler implements ActionListener{
	JTextArea textField;
	FilePanel filePanel;

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		//¸ßÁÁ
		case "123":
			System.out.println("¸ßÁÁ£¡£¡");
			System.out.println(textField.toString());
			Highlighter highLighter = textField.getHighlighter();
			System.out.println("£¡£¡");
			DefaultHighlightPainter	painter = new DefaultHighlightPainter(Color.cyan);
			int pos = textField.getCaretPosition();
			String text = textField.getText();
			int start = Math.min(pos, text.length()-1),end = pos;
			System.out.println(start + " " + pos + " " + end);
			while(start > 0 && text.charAt(start)!='\n') start--;
			while(end < text.length() && text.charAt(end) != '\n') end++;
			System.out.println(start + " " + pos + " " + end);
			try {
				highLighter.addHighlight(start, end, painter);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break;

		default:
			break;
		}
		
	}
	
	public void setTextField(JTextArea textField) {
		this.textField = textField;
	}

}
