package gui;

/*
 * 用户登录页面
 */
import db.UserDao;

import javax.swing.*;
import java.awt.event.MouseEvent;
import bean.User;
import service.KeyUtil;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;

public class UserLogin extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	JFrame jfm = new JFrame();
	// 设置窗口


	public UserLogin() {
		jfm.setTitle("super VCD 用户登录");
		jfm.setSize(450, 400);

		Container conr= jfm.getContentPane();
		JPanel jpl = new JPanel();

		// 设置按钮组件
		JButton login = new JButton("确定");
		JButton btn = new JButton("注册");
		JButton quit = new JButton("取消");

		login.setBounds(50, 250, 80, 30);
		btn.setBounds(175, 250, 80, 30);
		quit.setBounds(300, 250, 80, 30);

		conr.add(login);
		conr.add(btn);
		conr.add(quit);


		// 设置文本组件
		JLabel label1 = new JLabel("账号:");
		JLabel label2 = new JLabel("密码:");
		label1.setBounds(70, 120, 30, 30);
		label2.setBounds(70, 160, 30, 30);
		conr.add(label1);
		conr.add(label2);


		//设置文本输入框
		JTextField uname = new JTextField(30);
		JTextField pword = new JTextField(30);
		uname.setBounds(120, 120, 200, 30);
		pword.setBounds(120, 160, 200, 30);
		conr.add(uname);
		conr.add(pword);

		conr.add(jpl);

		// 关闭窗口行为
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfm.setLocationRelativeTo(null); // 窗口居中
		jfm.setVisible(true); // 窗体可见


		/*
		 * 监听事件
		 */

		// 取消监听
		quit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				uname.setText("");
				pword.setText("");
			}
		});

		// 注册监听
		btn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new modle.UserEnroll();
			}
		});

		// 确定登录监听
		login.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String username = uname.getText();
				String  password = pword.getText();
				boolean b = false ;

				User user = new User();
				user.setUserName(username);
				user.setPassword("");

				UserDao userDao = new UserDao();
				try {
					b = userDao.selectUser(user).getPassword().equals(KeyUtil.encrypt(password));
				} catch (SQLException e1) {
					e1.printStackTrace();
					b = false;
				}

				if(b==true) {
					UserWindow frame = new UserWindow();
					frame.setVisible(true);
					jfm.setVisible(false); // 窗体撤销
				}

				else {
					JOptionPane.showMessageDialog(null, "登录失败");
				}

			}
		});

	}

	public static void main(String[] args) throws Exception {

		new UserLogin();

	}

}

 