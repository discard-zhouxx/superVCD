package service;

import bean.Music;
import bean.User;
import db.MusicDao;
import db.UserDao;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketOfService {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7410);
            Socket socket = serverSocket.accept();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                    /*
                    1、处理 str ，将str分为几个部分{标识，信息}
                    2、添加switch 判断str的开头，通过不同的标识调用不同的方法
                    3、整理str，将信息参数写入
                    标识定为：
                    A login
                    B register
                    C update
                    D create
                    E selectFromName
                    F selectFromStyle
                     */
                System.out.println(str);
                String[] strings = str.split(" ");
                //此处之前应该加上判断标识
                if (strings[0].equals("login") || strings[0].equals("register") || strings[0].equals("update")) {
                    UserDao userDao = new UserDao();
                    User user = new User();
                    user.setUserName(strings[1]);
                    user.setPassword(KeyUtil.encrypt(strings[2]));//使用md5进行字符串处理

                    switch (strings[0]) {
                        case ("login"):
                            printWriter.print(userDao.selectUser(user));
                            printWriter.write("\n");
                            printWriter.flush();
                            break;
                        case ("register"):

                            printWriter.print( userDao.addUser(user));
                            printWriter.write("\n");
                            printWriter.flush();
                            break;
                        case ("update"):
                            printWriter.print(userDao.updateUser(user, strings[3]));
                            printWriter.write("\n");
                            printWriter.flush();
                            break;
                    }
                } else {
                    Music music = new Music();
                    MusicDao musicDao = new MusicDao();
                    music.setMusicName(strings[1]);
                    music.setImage(strings[2]);
                    music.setSinger(strings[3]);
                    music.setAlbum(strings[4]);
                    music.setStyle(strings[5]);

                    switch (strings[0]) {
                        case ("create"):
                            printWriter.print(musicDao.createMusic(music));
                            printWriter.write("\n");
                            printWriter.flush();
                            break;

                        case ("selectFromName"):
                            printWriter.print(musicDao.selectMusicByName(strings[1], Integer.parseInt(strings[2]), Integer.parseInt(strings[3])));
                            printWriter.write("\n");
                            printWriter.flush();
                            break;

                        case ("selectFromStyle"):
                            printWriter.print(musicDao.selectMusicByStyle(strings[4], Integer.parseInt(strings[2]), Integer.parseInt(strings[3])));
                            printWriter.write("\n");
                            printWriter.flush();
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
