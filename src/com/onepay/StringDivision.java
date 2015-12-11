package com.onepay;

/**
 * created by IntelliJ IDEA
 *  分割字符串
 * @author zjc
 * @time 2015/11/11-11:37
 */
public class StringDivision {
     public static void main(String[] args){
          String address="latitude:23.051758,longitude:113.381067,addr:广东省广州市番禺区大学城外环西路 378号";
          String[] sort=address.split(",");
          String[] s=sort[0].split(":");
          System.out.println(s[1]);

         String[] sort1=address.split(",");
         String[] s1=sort1[1].split(":");
         System.out.println(s1[1]);
     }
}
