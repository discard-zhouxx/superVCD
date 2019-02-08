package modle;

import bean.User;
import db.UserDao;
import service.KeyUtil;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserEnroll extends JFrame {


    private static final long serialVersionUID = 1L;
    JFrame frm = new JFrame();

    public UserEnroll() {

        // 设置窗口
        frm.setTitle("注册账号");
        frm.setSize(450, 450);
        Container cner = frm.getContentPane();

        JPanel jpl1 = new JPanel();

        // 按钮组件
        JButton save = new JButton("保存");
        JButton quit = new JButton("取消");
        save.setBounds(100, 250, 80, 30);
        quit.setBounds(250, 250, 80, 30);
        cner.add(save);
        cner.add(quit);

        //文本组件
        JLabel labelAlbum = new JLabel("新账号:");
        JLabel labelPassword = new JLabel("设置密码:");

        labelAlbum.setBounds(70, 100, 60, 40);
        labelPassword.setBounds(70, 160, 60, 40);

        cner.add(labelAlbum);
        cner.add(labelPassword);


        // 文本框组件
        JTextField fielAlbum = new JTextField();
        JTextField fielPassword = new JTextField();

        fielAlbum.setBounds(140, 100, 240, 30);
        fielPassword.setBounds(140, 160, 240, 30);

        cner.add(fielAlbum);
        cner.add(fielPassword);


        cner.add(jpl1);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗口行为
        frm.setLocationRelativeTo(null); // 窗口居中
        frm.setVisible(true);// 窗体可见

        // 事件监听
        // 取消
        quit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                fielAlbum.setText("");
                fielPassword.setText("");
            }
        });

        // 录入
        save.addActionListener(new ActionListener() {
            @SuppressWarnings("resource")
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                String strAlum = fielAlbum.getText().replaceAll("=", "").trim();
                String strStar = fielPassword.getText().replaceAll("=", "").trim();
                boolean flag = false;

                UserDao userDao = new UserDao();
                User user = new User();
                user.setUserName(strAlum);
                user.setPassword(KeyUtil.encrypt(strStar));
                try {
                    flag = userDao.addUser(user);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                if (flag == false) {
                    JOptionPane.showMessageDialog(null, "注册失败！");
                    frm.setVisible(false);
                    return;
                }
                if (flag == true) {
                    JOptionPane.showMessageDialog(null, "注册成功！");

                    return;
                }
            }
        });

    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
