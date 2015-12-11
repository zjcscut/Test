package com.Token;



import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 令牌处理器
 *
 * @author Vicky
 * @emial eclipser@163.com
 */
public class TokenProcessor {

    private static TokenProcessor instance = new TokenProcessor();

    private long previous;

   public TokenProcessor() {
    }

    public static TokenProcessor getInstance() {
        return instance;
    }

    public synchronized String generateToken(String msg, boolean timeChange) {
        try {

            long current = System.currentTimeMillis();
            if (current == previous)
                current++;
            previous = current;
            /**
             * Instance may be "MD5" or "SHA-1"
             */
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            if (timeChange) {
                // byte now[] = (current+"").toString().getBytes();
                byte now[] = (new Long(current)).toString().getBytes();
                md.update(now);
            }
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }

        return sb.toString();
    }

    @Test
    public void TestToken(){
        //带true为动态令牌
        String token1 = generateToken("zjc",true);
        String token2 = generateToken("zjc",false);
        System.out.println("带时间戳:" + token1);
        System.out.println("不带时间戳:" + token2);
    }
}
