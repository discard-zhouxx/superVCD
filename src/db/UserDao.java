package db;

import bean.User;
import service.KeyUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    
    public boolean addUser(User vo) throws SQLException {

        String sql = "INSERT INTO user (user_name,password) VALUES (?,?)";
        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getUserName());
            pstmt.setString(2,vo.getPassword());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            DatabaseConnection.closeAll(conn,pstmt,rs);
        }
    }

    public boolean updateUser(User vo,String newPassword) throws SQLException {
        String sql = "UPDATE user SET password=? WHERE user_name=?";
        try {
            if (KeyUtil.encrypt(selectUser(vo).getPassword()).equals(KeyUtil.encrypt(vo.getPassword()))){
                conn =  DatabaseConnection.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,newPassword);
                pstmt.setString(2,vo.getUserName());
                pstmt.executeUpdate();
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            DatabaseConnection.closeAll(conn,pstmt,rs);
        }
    }

    public User selectUser(User vo) throws SQLException {
        String sql = "SELECT password FROM user WHERE user_name=?";
        try {
            User user = new User();
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getUserName());
            rs = pstmt.executeQuery();
            while (rs.next()){
                user.setPassword(rs.getNString("password"));
            }
            user.setUserName(vo.getUserName());
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            DatabaseConnection.closeAll(conn,pstmt,rs);
        }
    }
}
