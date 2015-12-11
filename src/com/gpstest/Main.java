package com.gpstest;

/**
 * created by IntelliJ IDEA
 *
 * @author zjc
 * @time 2015/11/12-9:59
 */
public class Main {
    public static void main(String[] args){
       double g=GoogleGPS.GetDistance(113.3545330000,23.1421080000,113.3810670000,23.0517580000);
        double m=MyGPS.GetDistance(23.1421080000,113.3545330000,23.0517580000,113.3810670000);
        System.out.println("google:"+g);
        System.out.println("mine:"+m);
    }
}
