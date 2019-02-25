package com.mubu2;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

public class Mubu_main extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 *@function:主页面，两个jpanel a,b，在一个jframe中切换
	 */
	Main a;
	FilePanel b;
	Mubu_main()
	{
		a=new Main();
		setBackground(Color.WHITE);
		setSize(700,850);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //置窗口是否可以关闭
		this.setLayout(new BorderLayout());
		this.add(a);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		b=new FilePanel();     
		System.out.println("主页面");
	}
	void change1(int index) //切换页面的基本方法
	{
		b=new FilePanel(); //item_file[index]就是要在文本编辑页面呈现的内容
		a.setVisible(false);
		this.add(b);
		b.setVisible(true);
	}
	void change2() //切换页面的基本方法
	{
		a.setVisible(true);
		this.add(a);
		b.setVisible(false);
	}
	public static void main(String args[])
	{
		Mubu_main mubu=new Mubu_main();
		mubu.setTitle("幕布");
		
	}
}

