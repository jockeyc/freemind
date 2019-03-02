package com.mubu2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

public class FilePanelButtonHandler implements ActionListener{
	JTextArea textField ;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sourse = e.getActionCommand();
		System.out.println(sourse);
		switch (sourse) {
		case "����":{
			Highlighter highLighter = textField.getHighlighter();
			DefaultHighlightPainter	painter = new DefaultHighlightPainter(Color.cyan);
			int pos = textField.getCaretPosition();
			String text = textField.getText();
			if(pos == text.length() || text.charAt(pos) == '\n') pos--;
			int start = pos,end = pos;
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
		}		
		case "��������":{
			int pos = textField.getCaretPosition();
			String text = textField.getText();
			int start=pos;
			if(text.charAt(start)=='\n') start--;
			while(start > 0 && text.charAt(start)!='\n') start--;
			System.out.println("pos"+pos+" "+"start"+start);
			StringBuilder sb = new StringBuilder(text);
			sb.insert(start, "\t");
			text = sb.toString();
			textField.setText(text);
			textField.setCaretPosition(pos);
			break;
		}
		case "��������":{
			int pos = textField.getCaretPosition();
			String text = textField.getText();
			int start=pos;
			if(text.charAt(start)=='\n') start--;
			while(start > 0 && text.charAt(start)!='\n') start--;
			System.out.println("pos"+pos+" "+"start"+start);
			StringBuilder sb = new StringBuilder(text);
			if( text.charAt(start)=='\t') {
				System.out.println("start");
				sb.delete(start, start+1);
			}
				
			text = sb.toString();
			textField.setText(text);
			textField.setCaretPosition(pos);
			break;
		}
			
			

		default:
			break;
		}
		
	}
	
	public void setTextField(JTextArea textField) {
		this.textField = textField;
	}

}
