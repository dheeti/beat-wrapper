package com.dheeti.beat.wrapper.helper;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Created by jayramj on 13/9/14.
 */
public class EncryptUtil {

    public static boolean comparePassword(String encryptedPassword, String enteredPassword){
        boolean matched = false;
//        String pw_hash = BCrypt.hashpw(enteredPassword, BCrypt.gensalt());
        matched = BCrypt.checkpw(enteredPassword, encryptedPassword);
        return matched;
    }
}
