package com.CheckCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * created by IntelliJ IDEA
 *
 * @author zjc
 * @time 2015/11/20-13:21
 */
public class Test {
   public static void main(String[] args){
       String cellphone="15118850470";
       Pattern p = Pattern.compile("^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
       Matcher m = p.matcher(cellphone);
       boolean telFlag = m.matches();
       System.out.println(telFlag);
   }
}
