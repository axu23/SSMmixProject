package com.itcast;

import com.sun.imageio.plugins.png.PNGImageReader;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public static String getPassword(String password){
        String encode = bCryptPasswordEncoder.encode(password);
        return encode;
    }

    public static void main(String[] args) {
        String password = getPassword("awei");
        System.out.println(password);
    }
}
