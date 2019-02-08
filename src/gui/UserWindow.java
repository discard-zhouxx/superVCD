package gui;

import bean.Music;
import db.MusicDao;


import java.sql.SQLException;
import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/*
 * 操作页面
 */
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class UserWindow extends JFrame  {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;

	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

			}
		});
	}

	public UserWindow() {
		this.setTitle("super VCD 查询");
		this.setSize(450, 400);
		this.setLocationRelativeTo(null); // 窗口居中
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 创建菜单栏
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		// 创建菜单
		JMenu menu = new JMenu("文件");
		menuBar.add(menu);

		// 创建菜单项
		JMenuItem item1 = new JMenuItem("资料录入");
		JMenuItem item2 = new JMenuItem("修改密码");
		JMenuItem item3 = new JMenuItem("退出");

		// 添加菜单项
		menu.add(item1);
		menu.addSeparator(); // 添加分隔符
		menu.add(item2);
		menu.addSeparator();
		menu.add(item3);

		// 窗口设置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);

		// 资料录入
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserPage();

			}
		});

		// 修改密码
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserChange();

			}
		});

		// 退出系统
		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// 创建文本域
		this.setLayout(new BorderLayout());
		JTextArea TestContent = new JTextArea(20, 20);

		// 创建滚动面板,存放表格
		JScrollPane contentPane = new JScrollPane(TestContent);
		TestContent.setEditable(false); // 文本域不可编辑

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 创建搜索框
		JTextField collect = new JTextField();
		collect.setBounds(100, 13, 250, 24);
		contentPane.add(collect);
		collect.setColumns(20);

		// 创建按钮
		JButton searchButton = new JButton("搜索");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableModel.setRowCount(0);
				MusicDao musicDao = new MusicDao();
				Music music = new Music();
				String[] musics;

				try {
					List<Music> data = musicDao.selectMusicByName(collect.getText(),1,10);
					for (int i = 0;i<=data.size()-1;i++){
						music = data.get(i);
						musics = new String[]{music.getAlbum(),music.getMusicName(),music.getSinger(),music.getStyle(),music.getImage()};
						tableModel.addRow(musics);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		searchButton.setBounds(360, 13, 95, 24);
		contentPane.add(searchButton);


		/**
		 * 建立JTable，存文件中的数据
		 */
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBounds(80, 62, 430, 276);
		contentPane.add(tableScrollPane);

		/**
		 * 建立TJable上的解释栏
		 */
		tableModel.addColumn("歌曲");
		tableModel.addColumn("专辑");
		tableModel.addColumn("歌手");
		tableModel.addColumn("地区");
		tableModel.addColumn("图片路径");


	}
}

