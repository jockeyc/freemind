package com.mubu2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FilePanel_handler implements ActionListener {
	String path;
	JTextArea textField;
	JTextField textArea_1;
	JFrame jFrame;
	void setPath(String path)
	{
		this.path=path;
	}
	void settextField(JTextArea textField)
	{
		this.textField=textField;
	}
	void settextArea_1(JTextField textArea_1)
	{
		this.textArea_1=textArea_1;
	}
	void setjFrame(JFrame jFrame)
	{
		this.jFrame=jFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
//		if(e.getSource()==btnNewButton_1){
			System.out.println("�����ļ�");
        	File data=new File(path);
			String title=textArea_1.getText();
			try {
				//�����ļ�����ʵ�����Ǹ����ļ���·��
				if(title.equals(data.getName())==false)
				{
					int index=path.lastIndexOf("\\");
					String path1=path.substring(0,index+1);
					String path2=path1+title+".txt";
					this.path=path2;
					data.renameTo(new File(path));
				}
				data.createNewFile();
				String detail=textField.getText();
				FileWriter fw=new FileWriter(path);
				fw.write(detail);
				fw.close();
				jFrame.setContentPane(Main.getInstance(jFrame));
				jFrame.validate();//ˢ��
				
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
        }
//    }
}
