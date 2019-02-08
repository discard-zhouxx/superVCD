package gui;

/*
 * 信息录入页面
 */

import bean.Music;
import db.MusicDao;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.sql.SQLException;

public class UserPage extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JFrame frm = new JFrame();

    public UserPage() {

        // 设置窗口
        frm.setTitle("资料录入");
        frm.setSize(500, 550);
        Container cner = frm.getContentPane();

        JPanel jpl1 = new JPanel();

        // 按钮组件
        JButton save = new JButton("保存");
        JButton quit = new JButton("取消");
        save.setBounds(100, 400, 80, 30);
        quit.setBounds(250, 400, 80, 30);
        cner.add(save);
        cner.add(quit);

        //文本组件
        JLabel labelAlbum = new JLabel("专辑:");
        JLabel labelMusic = new JLabel("歌手:");
        JLabel labelNumber = new JLabel("风格:");
        JLabel labelYear = new JLabel("图片:");
        JLabel labelName = new JLabel("歌名:");
        labelAlbum.setBounds(70, 60, 60, 40);
        labelMusic.setBounds(70, 120, 60, 40);
        labelNumber.setBounds(70, 180, 60, 40);
        labelYear.setBounds(70, 240, 60, 40);
        labelName.setBounds(70, 300, 60, 40);
        cner.add(labelAlbum);
        cner.add(labelMusic);
        cner.add(labelNumber);
        cner.add(labelYear);
        cner.add(labelName);

        // 文本框组件
        JTextField fielAlbum = new JTextField();
        JTextField fielMusic = new JTextField();
        JTextField fielNumber = new JTextField();
        JTextField fielYear = new JTextField();
        JTextField fielName = new JTextField();
        fielAlbum.setBounds(140, 60, 240, 30);
        fielMusic.setBounds(140, 120, 240, 30);
        fielNumber.setBounds(140, 180, 240, 30);
        fielYear.setBounds(140, 240, 240, 30);
        fielName.setBounds(140, 300, 240, 30);
        cner.add(fielAlbum);
        cner.add(fielMusic);
        cner.add(fielNumber);
        cner.add(fielYear);
        cner.add(fielName);

        cner.add(jpl1);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗口行为
        frm.setLocationRelativeTo(null); // 窗口居中
        frm.setVisible(true);// 窗体可见

        // 事件监听
        // 取消
        quit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                fielAlbum.setText("");
                fielMusic.setText("");
                fielNumber.setText("");
                fielYear.setText("");
                fielName.setText("");
            }
        });

        // 录入
        save.addActionListener(new ActionListener() {
            @SuppressWarnings("resource")
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                String strAlum = fielAlbum.getText().replaceAll("|", "").trim();
                String strStar = fielMusic.getText().replaceAll("|", "").trim();
                String strColt = fielNumber.getText().replaceAll("|", "").trim();
                String strImg = fielYear.getText().replaceAll("|", "").trim();
                String strName = fielName.getText().replaceAll("|", "").trim();
                boolean flag = false;

                MusicDao musicDao = new MusicDao();
                Music music = new Music();
                music.setStyle(strColt);
                music.setSinger(strStar);
                music.setAlbum(strAlum);
                music.setImage(strImg);
                music.setMusicName(strName);
                try {
                    flag = musicDao.createMusic(music);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                String strEncode = "UTF-8";
                File filFile = new File("E:\\桌面\\information\\songs.txt");

                if (flag == true) {

                    JOptionPane.showMessageDialog(null, "保存成功！");

                    return;
                }
                if (flag == false) {
                    JOptionPane.showMessageDialog(null, "失败");

                    return;
                }


            }
        });

    }

    public static void main(String[] args) {
        //new UserPage();
    }

}
