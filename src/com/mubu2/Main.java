package com.mubu2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Main extends JPanel {
	/**
	 * @funtion:主页面
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel menu;     //按钮列表
	//我只搞了三个按钮，可以酌情添加
	JButton button_create;  //新建按钮
	JButton button_open;    //打开按钮
	JButton button_pages;   //查找按钮
	JTextField searchFile;
	JTable table;          //文件列表
	public DefaultTableModel model;
	JPanel FileList;
	JScrollPane Scroll;
	public Item_file[] item_file;  //记录文件data.txt中的内容，用于文件列表的显示
    ButtonHandler handler;     //主页面事件实现类
    JFrame jFrame;
	
	public Main(JFrame jFrame)
	{
		this.jFrame=jFrame;
		handler=new ButtonHandler();
		//读取已存储数据
		openFileDetail();
		//设计界面
		System.out.println("初始化对象");
		Color menu_color=new Color(230,222,233);
		setSize(700,800);
		menu=new JPanel();
		menu.setBackground(menu_color);
		menu.setLayout(null);
		menu.setBorder(BorderFactory.createEtchedBorder());
		menu.setFont(new Font("宋体",Font.PLAIN,15));
		menu.setPreferredSize(new Dimension(110,400));
		ImageIcon a =new ImageIcon("src/add.png");
		a.setImage(a.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		button_create=new JButton("新建",a);
		button_create.setBackground(menu_color);
		button_create.setFocusPainted(false);
		button_create.setBorderPainted(false);
		button_create.setHorizontalTextPosition(SwingConstants.CENTER);
		button_create.setVerticalTextPosition(SwingConstants.BOTTOM);
		button_create.setActionCommand("button_create");
		button_create.setBounds(10, 70, 90, 90);
		menu.add(button_create);
		
		a =new ImageIcon("src/folder_open.png");
		a.setImage(a.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		button_open=new JButton("打开",a);
		button_open.setBackground(menu_color);
		button_open.setFocusPainted(false);
		button_open.setBorderPainted(false);
		button_open.setHorizontalTextPosition(SwingConstants.CENTER);
		button_open.setVerticalTextPosition(SwingConstants.BOTTOM);
		button_open.setActionCommand("button_open");
		button_open.setBounds(10, 210, 90, 90);
		menu.add(button_open);
		
		a =new ImageIcon("src/search.png");
		a.setImage(a.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		button_pages=new JButton("查找",a);
		button_pages.setBackground(menu_color);
		button_pages.setFocusPainted(false);
		button_pages.setBorderPainted(false);
		button_pages.setHorizontalTextPosition(SwingConstants.CENTER);
		button_pages.setVerticalTextPosition(SwingConstants.BOTTOM);
		button_pages.setActionCommand("button_pages");
		button_pages.setBounds(10, 350, 90, 90);
		menu.add(button_pages);
		
		searchFile=new JTextField("请输入文件名");
		searchFile.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				JTextField textField = (JTextField)e.getSource();
				String temp = textField.getText();
				if(temp.equals("")) {
					textField.setText("请输入文件名");
					textField.setForeground(Color.BLACK);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				JTextField textField = (JTextField)e.getSource();
				String temp = textField.getText();
				if(temp.equals("请输入文件名")) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}
		});
		searchFile.addActionListener(handler);
		searchFile.setActionCommand("button_pages");
		searchFile.setBounds(5, 440, 100, 20);
		menu.add(searchFile);
		
		FileList=new JPanel();
		FileList.setBackground(Color.WHITE);
		FileList.setLayout(new BorderLayout());
		
		JPanel detail=new JPanel();
		detail.setBackground(Color.WHITE);
		detail.setLayout(new BorderLayout());
		JLabel name=new JLabel("名称                                  ");
		name.setBorder(null);
		name.setBackground(Color.WHITE);
		name.setFont(new Font("等线",Font.PLAIN,10));
		JLabel time=new JLabel("                   |修改时间");
		time.setBorder(null);
		time.setBackground(Color.WHITE);
		time.setFont(new Font("等线",Font.PLAIN,10));
		JLabel path=new JLabel("|路径                                                     ");
		path.setBorder(null);
		path.setBackground(Color.WHITE);
		path.setFont(new Font("等线",Font.PLAIN,10));
		detail.add(name,BorderLayout.WEST);
		detail.add(time,BorderLayout.CENTER);
		detail.add(path,BorderLayout.EAST);
		FileList.add(detail,BorderLayout.NORTH);
		createTable();
		table=new JTable(model){
		    /**
			 * @funtion:设置不可编辑
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int ColIndex){
	             return false;
	            }
//			public Component prepareRenderer(TableCellRenderer renderer,int row, int column) 
//			{
//					Component c = super.prepareRenderer(renderer, row, column);
//					if (c instanceof JComponent) 
//					{
//						((JComponent) c).setOpaque(false);
//					}
//					return c;
//			}
	    };
	    table.setOpaque(false); // 设置jtable本身为透明的
		table.setFont(new Font("宋体",Font.PLAIN,15));
		table.setShowGrid(false);
		table.setRowHeight(19);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ImageIcon icon = new ImageIcon("bizhi.png");
		icon.setImage(icon.getImage().getScaledInstance(700,850,Image.SCALE_DEFAULT));
		JLabel lab = new JLabel(icon); // 将图片放入到label中
		lab.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置放有图片的label的位置
		FileList.add(lab,BorderLayout.CENTER, -1); 
		FileList.add(table,BorderLayout.CENTER,0);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				if(e.getClickCount()==2)
				{
					int count=table.getSelectedRow();
					/*
					 * 添加想写的代码
					 */
					FileHandler fileHandler = new FileHandler();
					System.out.println(count + " " + fileHandler.data.files.size());
					Item_file item = fileHandler.data.files.get(count);
					fileHandler.open(jFrame, new File(item.path), item.name);
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				this_mousePressed(e);
			}
		});
		
		Scroll=new JScrollPane();
		Scroll.setViewportView(FileList);
		
		this.setLayout(new BorderLayout());
		this.add(menu,BorderLayout.WEST);
		this.add(Scroll,BorderLayout.CENTER);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		
		//向handler传递控件
		handler.setSearchFile(searchFile);
		handler.setTable(table);
		handler.setDefaultTableModel(model);
		handler.setjframe(jFrame);
		button_pages.addActionListener(handler);
		button_open.addActionListener(handler);
		button_create.addActionListener(handler);
	}
	protected void this_mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mods=e.getModifiers();
		//鼠标右键
		if((mods&InputEvent.BUTTON3_MASK)!=0){
		//弹出菜单
			MyPopupMenu myPopupMenu = new MyPopupMenu();
			myPopupMenu.setTable(table);
			myPopupMenu.setModel(model);
			myPopupMenu.setSearchFile(searchFile);
			myPopupMenu.setjFrame(jFrame);
			myPopupMenu.init();
			myPopupMenu.show(this.getParent(), e.getX()+110, e.getY()+15);
		}
	}
	//打开记录文件data.txt并保存在item_file[]中
	private void openFileDetail() {
		// TODO 自动生成的方法存根
		int count,i=0;
		try 
		{
			File data=new File("src/data.txt");
			InputStreamReader reader = new InputStreamReader(  
                    new FileInputStream(data)); // 建立一个输入流对象reader  
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
            String line = "";  
            line = br.readLine();
            count=Integer.parseInt(line);
            item_file=new  Item_file[count];
            
            line=br.readLine();
            while (line != null && i<count) 
            {
            	String s[]=line.split("\t");
                line = br.readLine(); // 一次读入一行数据
                item_file[i]=new Item_file(s[0],s[1],s[2]);
                i++;
            }
            br.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	//用item_file[]初始化文件列表
	void createTable()
	{
		Vector data=new Vector();
		int count=item_file.length;
		for(int i=0;i<count;i++)
		{
			Vector<String> d=new Vector<String>();
			d.add(0,item_file[i].getName());
			d.add(1,item_file[i].getTime());
			d.add(2,item_file[i].getPath());
			data.add(d);
		}
		Vector<String> names=new Vector<String>();
		names.add("");
		names.add("");
		names.add("");
		model=new DefaultTableModel(data,names);
	}
	
	private static Main panel_02=null;
    //对外接口
    public static Main getInstance(JFrame jFrame){
        panel_02 = new Main(jFrame);
        return panel_02;
    }

	Item_file[] getItem_file()
	{
		return item_file;
	}
}
