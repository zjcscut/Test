package com.Token;

import com.sun.xml.internal.messaging.saaj.util.Base64;
import org.junit.Test;

import java.security.MessageDigest;

/**
 * created by IntelliJ IDEA
 *
 * @author zjc
 * @time 2015/12/4-10:21
 */
public class TestToken {
    @Test
    public  void Token(){
        try {
            String username = "Zjc";
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(username.getBytes());
            System.out.println(toHex(messageDigest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    /**
     * 将16位byte[] 转换为32位String
     *
     * @param buffer
     * @return
     */
    private String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }

        return sb.toString();
    }
}
