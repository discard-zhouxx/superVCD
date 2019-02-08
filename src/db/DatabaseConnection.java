package db;

import java.sql.*;

public class DatabaseConnection {
    private static final String user = "root";
    private static final String password = "VanByZ1997.";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/super_vcd?useSSL=false&serverTimezone=UTC";

    static {
        try {
            Class.forName(driver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
        if(rs!=null) {
            rs.close();
        }
        if(pstmt!=null) {
            pstmt.close();
        }
        if(conn!=null) {
            conn.close();
        }
    }
}
