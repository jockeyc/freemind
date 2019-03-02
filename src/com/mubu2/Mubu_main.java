package com.mubu2;

import java.awt.Color;
import javax.swing.JFrame;

public class Mubu_main extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 *@function:主页面，两个jpanel a,b，在一个jframe中切换
	 */

	private static Mubu_main single=null;
	public static Mubu_main getInstance(){
        if(single==null)
            single = new Mubu_main();
        return single;
    }

	Mubu_main()
	{
		setBackground(Color.WHITE);
		setSize(700,850);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //置窗口是否可以关
		setContentPane(Main.getInstance(this));
		System.out.println("主页面");
	}

	public static void main(String args[])
	{
		Mubu_main mubu=new Mubu_main();
		mubu.setTitle("幕布");
		mubu.setVisible(true);
	}
}

