package com.mubu2;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

public class Mubu_main extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 *@function:��ҳ�棬����jpanel a,b����һ��jframe���л�
	 */
	Main a;
	FilePanel b;
	Mubu_main()
	{
		a=new Main();
		setBackground(Color.WHITE);
		setSize(700,850);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //�ô����Ƿ���Թر�
		this.setLayout(new BorderLayout());
		this.add(a);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		b=new FilePanel();     
		System.out.println("��ҳ��");
	}
	void change1(int index) //�л�ҳ��Ļ�������
	{
		b=new FilePanel(); //item_file[index]����Ҫ���ı��༭ҳ����ֵ�����
		a.setVisible(false);
		this.add(b);
		b.setVisible(true);
	}
	void change2() //�л�ҳ��Ļ�������
	{
		a.setVisible(true);
		this.add(a);
		b.setVisible(false);
	}
	public static void main(String args[])
	{
		Mubu_main mubu=new Mubu_main();
		mubu.setTitle("Ļ��");
		
	}
}

