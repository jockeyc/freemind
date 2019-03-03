package com.mubu2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

import com.mubu2.Tree.TreePanel;

import javax.swing.text.Highlighter;

import java.awt.Dimension;

public class FilePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textField;
	ButtonHandler handler;
	FilePanelButtonHandler filePanelButtonHandler;
	FilePanelKeyListener filePanelKeyListener;
	private TreePanel panel_2;
	public FilePanel(JFrame jFrame,String path) 
	{
	setBackground(new Color(240, 248, 255));

	setBounds(100, 100, 801, 699);
	contentPane = new JPanel();
	contentPane.setToolTipText("\u6587\u672C\u7F16\u8F91\u9875\u9762");
	contentPane.setSize(new Dimension(700, 850));
	contentPane.setFont(new Font("楷体", Font.BOLD, 15));
	contentPane.setBackground(new Color(245, 255, 250));
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
	JPanel panel = new JPanel();
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	JButton btnNewButton_1 = new JButton("保存并退出");
	JLabel label = new JLabel("\u5F53\u524D\u5317\u4EAC\u65F6\u95F4\uFF1A00:00:00");
	label.setFont(new Font("宋体", Font.BOLD, 15));
	TimerHandler timerHandler = new TimerHandler();
	timerHandler.setjLabel(label);
	Timer clock = new Timer(1000, timerHandler);
	clock.start();
	
	GroupLayout gl_contentPane = new GroupLayout(contentPane);
	gl_contentPane.setHorizontalGroup(
		gl_contentPane.createParallelGroup(Alignment.TRAILING)
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGap(10)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)))
				.addGap(57))
	);
	gl_contentPane.setVerticalGroup(
		gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGap(23)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
					.addComponent(panel, 0, 0, Short.MAX_VALUE)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE))
				.addGap(104))
	);
	
	JButton btnNewButton = new JButton("");
	btnNewButton.setActionCommand("增加缩进");
	btnNewButton.setToolTipText("\u589E\u52A0\u7F29\u8FDB");
	btnNewButton.setIcon(new ImageIcon("src/img/tab.png"));
	
	JButton button_1 = new JButton();
	button_1.setToolTipText("转换");
	button_1.setIcon(new ImageIcon("src/img/refresh.png"));
	button_1.setActionCommand("转换");
	
	JButton button_2 = new JButton("");
	button_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
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
			
			if(line.indexOf('·') != -1) {
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
			textField.setText(text);
			
			in.close();
		}
	});
	button_2.setToolTipText("\u5220\u9664\u9879");
	button_2.setIcon(new ImageIcon("src/img/delete.png"));
	
	JButton button_3 = new JButton("");
	button_3.setActionCommand("减少缩进");
	button_3.setToolTipText("\u51CF\u5C11\u7F29\u8FDB");
	button_3.setIcon(new ImageIcon("src/img/r_tab.png"));
	
	JButton button_4 = new JButton("");
	button_4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	});
	button_4.setToolTipText("\u6DFB\u52A0\u590D\u9009\u6846");
	button_4.setIcon(new ImageIcon("src/img/insert_img.png"));
	
	JButton button_5 = new JButton("");
	button_5.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	});
	button_5.setToolTipText("\u5B8C\u6210");
	button_5.setIcon(new ImageIcon("src/img/finished.PNG"));
	
	JButton button_6 = new JButton("");
	button_6.setActionCommand("高亮");
	button_6.setToolTipText("\u9AD8\u4EAE");
	button_6.setIcon(new ImageIcon("src/img/highlight.png"));
	
	JButton button_7 = new JButton("");
	button_7.setActionCommand("add_description");
	button_7.setToolTipText("\u7F16\u8F91\u63CF\u8FF0");
	button_7.setIcon(new ImageIcon("src/img/describe.PNG"));
	
	JButton button_8 = new JButton("");
	button_8.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	});
	button_8.setToolTipText("\u63D2\u5165\u56FE\u7247");
	button_8.setIcon(new ImageIcon("src/img/gxk.png"));
	
	JButton button_9 = new JButton("");
	button_9.setToolTipText("\u5C06\u601D\u7EF4\u5BFC\u56FE\u5BFC\u51FA\u4E3A\u56FE\u7247");
	button_9.setIcon(new ImageIcon("src/img/save_picture.png"));
	button_9.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	});
	GroupLayout gl_panel = new GroupLayout(panel);
	gl_panel.setHorizontalGroup(
		gl_panel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
					.addComponent(btnNewButton, 0, 0, Short.MAX_VALUE)
					.addComponent(button_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(button_5, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addComponent(button_6, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addComponent(button_7, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)
					.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)
					.addComponent(button_8, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addComponent(button_9, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
				.addContainerGap())
	);
	gl_panel.setVerticalGroup(
		gl_panel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel.createSequentialGroup()
				.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
				.addGap(1)
				.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
				.addGap(1)
				.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
				.addGap(2)
				.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
				.addGap(18)
				.addComponent(button_8, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
				.addGap(27)
				.addComponent(button_9, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(195, Short.MAX_VALUE))
	);
	panel.setLayout(gl_panel);
	
	JPanel panel_1 = new JPanel();
	tabbedPane.addTab("文字编辑区", null, panel_1, null);
	panel_1.setLayout(new BorderLayout(0, 0));
	
	JScrollPane scrollPane = new JScrollPane();
	panel_1.add(scrollPane, BorderLayout.CENTER);
	
	JPanel panel_3 = new JPanel();
	scrollPane.setColumnHeaderView(panel_3);
	
	JLabel lblNewLabel_1 = new JLabel("标题:   ");

	JTextField textArea_1 = new JTextField();

	textArea_1.setBorder(new LineBorder(Color.GRAY, 1, true));
	GroupLayout gl_panel_3 = new GroupLayout(panel_3);
	gl_panel_3.setHorizontalGroup(
		gl_panel_3.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel_3.createSequentialGroup()
				.addContainerGap()
				.addComponent(lblNewLabel_1)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 531, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(166, Short.MAX_VALUE))
	);
	gl_panel_3.setVerticalGroup(
		gl_panel_3.createParallelGroup(Alignment.TRAILING)
			.addGroup(gl_panel_3.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblNewLabel_1)
					.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
	);
	panel_3.setLayout(gl_panel_3);
	
	textField = new JTextArea();
	scrollPane.setViewportView(textField);
	textField.setColumns(10);
	if(path!=null)
	{
		try
		{
			File data=new File(path);
			String title=data.getName();
			title=title.substring(0,title.length()-4);
			textArea_1.setText(title);
			InputStreamReader reader = new InputStreamReader(  
					new FileInputStream(data)); // 建立一个输入流对象reader  
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
			String line=br.readLine();
			while(line!=null)
			{
				textField.append(line);
				textField.append("\r\n");
				line=br.readLine();
			}
			reader.close();
        }
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	String text = textField.getText();
	if(text.length()==0) {
		text+="○";
		textField.setText(text);
	}
	JPanel panel_2_m = new JPanel();
	panel_2_m.setLayout(new BorderLayout(0, 0));
	
	JScrollPane scrollPane_1 = new JScrollPane();
	panel_2_m.add(scrollPane_1, BorderLayout.CENTER);
	
	panel_2=new TreePanel(); 
	panel.setSize(100000,210000);
    panel_2.setBackground(new Color(240, 248, 255)); 
    panel_2.setGridColor(Color.white);  
    panel_2.setLinkLineColor(Color.BLACK);  
    panel_2.setStringColor(Color.BLACK);  
    scrollPane_1.setColumnHeaderView(panel_2);
    scrollPane_1.setViewportView(panel_2);
	tabbedPane.addTab("图形转化区", null, panel_2_m, null);
	contentPane.setLayout(gl_contentPane);

	handler=new ButtonHandler();
	handler.setPath(path);
	handler.settextField(textField);
	handler.settextArea_1(textArea_1);
	handler.setjFrame(jFrame);
	btnNewButton_1.setActionCommand("btnNewButton_1");
	btnNewButton_1.addActionListener(handler);
	
	filePanelButtonHandler = new FilePanelButtonHandler();
	filePanelButtonHandler.setTextField(textField);
	filePanelButtonHandler.settextArea_1(textArea_1);
	filePanelButtonHandler.setpanel_2(panel_2);
	btnNewButton.addActionListener(filePanelButtonHandler);//增加缩进
	button_1.addActionListener(filePanelButtonHandler);//转换为思维导图
	button_3.addActionListener(filePanelButtonHandler);//减少缩进
	button_6.addActionListener(filePanelButtonHandler);//高亮
	button_7.addActionListener(filePanelButtonHandler);//编辑描述
	
	
	filePanelKeyListener = new FilePanelKeyListener();
	filePanelKeyListener.setTextField(textField);
	textField.addKeyListener(filePanelKeyListener);
	
	
	add(contentPane);
	setVisible(true);
	}
	private static FilePanel panel_02=null;
    //对外接口
	public static FilePanel getInstance(JFrame jFrame,String path)
	{
		panel_02 = new FilePanel(jFrame,path);
		return panel_02;
	}

}
