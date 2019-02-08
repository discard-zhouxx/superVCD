package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KeyUtil {
    /**
     * 使用md5对密码进行加密
     * @param password 将密码传入
     * @return 返回加密过后的新密码
     */
    public static String encrypt(String password){
        String result = "";
        try {
            MessageDigest ms = MessageDigest.getInstance("MD5");
            ms.update(password.getBytes());
            byte s[ ]=ms.digest( );
            for (int i=0; i<s.length;i++){
                result+=Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
