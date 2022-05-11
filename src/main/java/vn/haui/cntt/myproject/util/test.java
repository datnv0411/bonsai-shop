package vn.haui.cntt.myproject.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class test {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String psw = "123456";

        String encoder = passwordEncoder.encode(psw);

        System.out.println(encoder);
    }
}
