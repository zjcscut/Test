package com.CheckCode;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * created by IntelliJ IDEA
 *
 * @author zjc
 * @time 2015/11/20-16:26
 */
public class TestMonth {

    public static void main(String[] args) {
        Map<String, Date> map = new HashMap<String, Date>();
        Calendar c = Calendar.getInstance();
        Date d1 = null;
        Date d2 = null;
        Integer year = c.get(Calendar.YEAR);
        Integer month = c.get(Calendar.MONTH);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        d1 = c.getTime();
        map.put("start", d1);
        // 某月的最后一天是该月的下一个月的第一天减去1
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.add(Calendar.DAY_OF_MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        d2 = c.getTime();
        map.put("end", d2);
        System.out.println(map);
    }

}
