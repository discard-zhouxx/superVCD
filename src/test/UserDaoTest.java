package test;

import bean.User;
import db.UserDao;
import service.KeyUtil;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args){
        User user = new User();
        user.setUserName("SocketTest1");
        user.setPassword("UserDaoTest5");
        UserDao userDao = new UserDao();
        try {
            //System.out.println(userDao.addUser(user));
            System.out.println(userDao.updateUser(user,KeyUtil.encrypt("UserDaoTest5")));
            System.out.println(userDao.selectUser(user).getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
