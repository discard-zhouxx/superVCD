package gui;

import bean.User;
import db.UserDao;
import service.KeyUtil;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;

public class UserChange extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JFrame frm = new JFrame();

    public UserChange() {
        // 设置窗口
        frm.setTitle("修改密码");
        frm.setSize(500, 550);
        Container cner = frm.getContentPane();
        frm.setLocationRelativeTo(null); // 窗口居中
        JPanel jpl1 = new JPanel();

        // 按钮组件
        JButton save = new JButton("保存");
        JButton quit = new JButton("取消");
        save.setBounds(100, 350, 80, 30);
        quit.setBounds(250, 350, 80, 30);
        cner.add(save);
        cner.add(quit);

        JLabel labelAlbum = new JLabel("账号");
        JLabel labelOld = new JLabel("原密码");
        JLabel labelNew = new JLabel("新密码");
        JLabel labelTwo = new JLabel("确认密码");
        labelAlbum.setBounds(70, 100, 60, 40);
        labelOld.setBounds(70, 160, 60, 40);
        labelNew.setBounds(70, 220, 60, 40);
        labelTwo.setBounds(70, 280, 60, 40);
        cner.add(labelAlbum);
        cner.add(labelOld);
        cner.add(labelNew);
        cner.add(labelTwo);

        // 文本框
        JTextField fielAlbum = new JTextField();
        JTextField fielOld = new JTextField();
        JTextField fielNew = new JTextField();
        JTextField fielTwo = new JTextField();
        fielAlbum.setBounds(140, 100, 240, 30);
        fielOld.setBounds(140, 160, 240, 30);
        fielNew.setBounds(140, 220, 240, 30);
        fielTwo.setBounds(140, 280, 240, 30);
        cner.add(fielAlbum);
        cner.add(fielOld);
        cner.add(fielNew);
        cner.add(fielTwo);

        cner.add(jpl1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗口行为
        frm.setVisible(true);

        // 取消
        quit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                fielAlbum.setText("");
                fielOld.setText("");
                fielNew.setText("");
                fielTwo.setText("");
            }
        });


        //保存
        save.addActionListener(new ActionListener() {
            private String strLineData;
            private BufferedReader br;

            @Override
            public void actionPerformed(ActionEvent e) {
                strLineData = null;
                String apw = fielAlbum.getText();//账号
                String opw = fielOld.getText();//原密码
                String npw = fielNew.getText();//新密码
                String tpw = fielTwo.getText();//再次输入
                boolean flag = false;

                UserDao userDao = new UserDao();
                User user = new User();

                user.setUserName(apw);
                user.setPassword(KeyUtil.encrypt(opw));



                if (npw.equals(opw)) {
                    JOptionPane.showMessageDialog(null, "密码未修改");
                    return;
                } else if (!npw.equals(tpw)) {
                    JOptionPane.showMessageDialog(null, "两次密码不一致,修改失败");
                    return;
                }
                try {
                    flag = userDao.updateUser(user, KeyUtil.encrypt(npw));

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (flag == true) {
                    JOptionPane.showMessageDialog(null, "保存成功！");
                    frm.setVisible(false); // 窗体撤销
                    new UserLogin();
                }

                if (flag == false) {
                    JOptionPane.showMessageDialog(null, "账号密码不正确");
                }
            }
        });

    }

    public static void main(String[] args) {

        new UserChange();
    }

}
