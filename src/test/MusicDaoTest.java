package test;

import bean.Music;
import db.MusicDao;

import java.sql.SQLException;

public class MusicDaoTest {
    public static void main(String[] args){
        Music music = new Music();
        MusicDao musicDao = new MusicDao();
        music.setStyle("大陆");
        music.setSinger("张学友");
        music.setAlbum("祝福");
        music.setImage("");
        music.setMusicName("祝福");
        try {
            //musicDao.createMusic(music);
            System.out.println(musicDao.selectMusicByStyle("大陆",1,2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
