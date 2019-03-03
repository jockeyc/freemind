package com.mubu2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

import com.mubu2.Tree.MuxTree;
import com.mubu2.Tree.Node;
import com.mubu2.Tree.TreePanel;

public class FilePanelButtonHandler implements ActionListener{
	JTextArea textField ;
	TreePanel panel_2;
	JTextField textArea_1;
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
			if(text.charAt(start)=='\t') {
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
		case "转换":{
			String[] lineString=textField.getText().split("\n");
			int count_string=lineString.length;
			if(count_string>0)
			{
				MuxTree a=new MuxTree(textArea_1.getText());
				MuxTree child=a.insert(lineString[0],a);
				int count_next=0,count;
				for(int i=1;i<count_string;i++)
				{
					count_next=countTab(lineString[i]);
					count=countTab(child.getData());
					if(lineString[i].indexOf("○")==-1)
						continue;       //判断是否为注释
					if(count_next==count)
					{
						child =child.parent.insert(lineString[i], child.parent);
					}
					else if(count_next>count)
					{
						child=child.insert(lineString[i], child);
					}
					else if(count_next<count && count_next>=0)
					{
						for(int i1=0;i1<=(count-count_next);i1++)
						{
							child=child.parent;
						}
					child=child.insert(lineString[i], child);
					}
				}
				//用tree来画思维导图
				Node tree = a.convert_toNode(a);
				panel_2.setTree(tree);
			}
		}
		default:
			break;
		}
	}
	public void setTextField(JTextArea textField) {
		this.textField = textField;
	}
	public void setpanel_2(TreePanel panel_2) {
		// TODO 自动生成的方法存根
		this.panel_2=panel_2;
	}
	void settextArea_1(JTextField textArea_1)
	{
		this.textArea_1=textArea_1;
	}
	int countTab(String line) {
		// TODO 自动生成的方法存根
		int count=0;
		char[] data=line.toCharArray();
		for(int i=0;i<line.length();i++)
		{
			if(data[i]=='\t')
			{
				count++;
			}
			else
				break;
		}
		return count;
	}
}
