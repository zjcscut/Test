package com.CheckCode;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * created by IntelliJ IDEA
 *
 * @author zjc
 * @time 2015/11/20-16:27
 */
public class TestDay {
   public static void main(String[] args){
       Map<String, Date> map = new HashMap<String, Date>();
       Calendar c = Calendar.getInstance();
       int date = c.get(Calendar.DATE);
       c.set(Calendar.DAY_OF_MONTH,date -1);
       c.set(Calendar.HOUR_OF_DAY, 0);
       c.set(Calendar.MINUTE, 0);
       c.set(Calendar.SECOND, 0);
       Date d1 = c.getTime();
       map.put("start", d1);

       c.set(Calendar.HOUR_OF_DAY, 23);
       c.set(Calendar.MINUTE, 59);
       c.set(Calendar.SECOND, 59);
       Date d2 = c.getTime();
       map.put("end", d2);
       System.out.println(map);
   }
}
