package db;

import bean.Music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MusicDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public boolean createMusic(Music vo) throws SQLException {
        try {
            String sql = "INSERT INTO music (music_name,image,singer,album,style) VALUES (?,?,?,?,?)";
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,vo.getMusicName());
            pstmt.setString(2,vo.getImage());
            pstmt.setString(3,vo.getSinger());
            pstmt.setString(4,vo.getAlbum());
            pstmt.setString(5,vo.getStyle());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseConnection.closeAll(conn,pstmt,rs);
        }
    }

    public List<Music> selectMusicByStyle(String musicStyle,int page,int pageSize) throws SQLException {
        List<Music> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM music WHERE style=? LIMIT ?,?";
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,musicStyle);
            pstmt.setInt(2,(page-1)*pageSize);
            pstmt.setInt(3,pageSize);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Music music = new Music();
                music.setMusicName(rs.getNString(1));
                music.setImage(rs.getNString(2));
                music.setSinger(rs.getNString(3));
                music.setAlbum(rs.getNString(4));
                music.setStyle(rs.getNString(5));
                list.add(music);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            DatabaseConnection.closeAll(conn,pstmt,rs);
        }
    }

    public List<Music> selectMusicByName(String musicName,int page,int pageSize) throws SQLException {
        List<Music> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM music WHERE music_name LIKE ? LIMIT ?,?";
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,musicName);
            pstmt.setInt(2,(page-1)*pageSize);
            pstmt.setInt(3,pageSize);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Music music = new Music();
                music.setMusicName(rs.getNString(1));
                music.setImage(rs.getNString(2));
                music.setSinger(rs.getNString(3));
                music.setAlbum(rs.getNString(4));
                music.setStyle(rs.getNString(5));
                list.add(music);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            DatabaseConnection.closeAll(conn,pstmt,rs);
        }
    }
}
