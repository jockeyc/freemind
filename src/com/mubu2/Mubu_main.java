package com.mubu2;

import java.awt.Color;
import javax.swing.JFrame;

public class Mubu_main extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 *@function:��ҳ�棬����jpanel a,b����һ��jframe���л�
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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //�ô����Ƿ���Թ�
		setContentPane(Main.getInstance(this));
		System.out.println("��ҳ��");
	}

	public static void main(String args[])
	{
		Mubu_main mubu=new Mubu_main();
		mubu.setTitle("Ļ��");
		mubu.setVisible(true);
	}
}

