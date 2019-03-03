package com.mubu2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

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
		case "高亮":{
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
		case "增加缩进":{
			int pos = textField.getCaretPosition();
			System.out.println(pos);
			String text = textField.getText();
			//if(pos == text.length() || text.charAt(pos) == '\n') pos--;
			int start=pos;
			while(start > 0 && text.charAt(start-1)!='\n') start--;
			System.out.println("pos"+pos+" "+"start"+start);
			StringBuilder sb = new StringBuilder(text);
			sb.insert(start, "\t");
			text = sb.toString();
			textField.setText(text);
			textField.setCaretPosition(start);
			break;
		}
		case "减少缩进":{
			int pos = textField.getCaretPosition();
			System.out.println(pos);
			String text = textField.getText();
			//if(pos == text.length() || text.charAt(pos) == '\n') pos--;
			int start=pos;
			while(start > 0 && text.charAt(start-1)!='\n') start--;
			System.out.println("pos"+pos+" "+"start"+start);
			StringBuilder sb = new StringBuilder(text);
			if( start!=text.length()&&text.charAt(start)=='\t') {
				System.out.println("start");
				sb.delete(start, start+1);
			}
			text = sb.toString();
			textField.setText(text);
			textField.setCaretPosition(start);
			break;
		}
		case "add_description":{
			int pos = textField.getCaretPosition();
			int count_tab=0;
			String text = textField.getText();
			int start=pos,end=pos;
			if(start!=text.length()&&text.charAt(start)=='\t') {
				while(text.charAt(start)=='\t') start++;
			}
			else {
				while(start > 0 && text.charAt(start-1)!='\t'&&text.charAt(start-1)!='\n') start--;
			}
			while(start > 0 && text.charAt(start-1)!='\n') {
				start--;
				count_tab++;
			}
			System.out.print("count:"+count_tab);
			while(end < text.length() && text.charAt(end) != '\n') end++;
			System.out.println("pos"+pos+" "+"start"+start);
			StringBuilder sb = new StringBuilder(text);
			if(end==text.length()) {
				sb.append("\n");
				for (int i = 0; i < count_tab; i++) {
						sb.append("\t");
				}
				
			}
			else {
				for (int i = 0; i < count_tab; i++) {
					try {
						sb.insert(end+1, "\t");
						end++;
					}
					catch (Exception e1) {
						// TODO: handle exception
					}	
				}
				sb.insert(end+1, "\n");
			}
			text = sb.toString();
			textField.setText(text);
			textField.getFocusListeners();
			textField.setCaretPosition(end);
			break;
		}
		case "delete":{
			String text = textField.getText();
			Scanner in = null;
			in = new Scanner(text);
			
			int pos = textField.getCaretPosition();
			int now = 0, left = 0, right = 0;
			String line = "";
			
			while(in.hasNext()) {
				line = in.nextLine();
				now += line.length()+1;
				if(now >= pos) {
					left = now-line.length()-1;
					right = now;
					break;
				}
			}
			
			if(line.indexOf('○') != -1) {
				while(right < text.length() ) {
					if(text.charAt(right++) == '\n') {
						break;
					}
				}
			}else {
				int cnt = 0;
				while(left > 0) {
					if(text.charAt(left) == '\n') {
						cnt++;
					}
					if(cnt == 2) {
						left++;
						break;
					}
					left--;
				}
			}
			int len = text.length();
			text = text.substring(0, left) + text.substring(Math.min(right, len),len);
			if(text.length()==0) {
				text="○";
			}
			textField.setText(text);
			
			in.close();
		}
			
			

		default:
			break;
		}
		
	}
	
	public void setTextField(JTextArea textField) {
		this.textField = textField;
	}

}
