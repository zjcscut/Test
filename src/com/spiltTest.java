package com;

/**
 * created by IntelliJ IDEA
 *
 * @author zjc
 * @time 2015/11/23-14:14
 */
public class spiltTest {
    public static void main(String[] args){
        String hql = "from OrderRepair model where ";

        String serviceScope ="111";
        String myType ="xxx";
        String OrderStr = "YYY";
        hql += " model.department = :department and";
        hql += " model.department  in ( " + serviceScope + " ) and";
        hql += " model.sentId != :receiveId and ";
        hql += " model.repairType in ( " + myType + " ) and ";
        hql += " model.orderNum not in ( " + OrderStr + " ) and";
        hql += " model.clientOrderStatus in (1,2) ";
        hql += " order by model.orderTime desc";
        System.out.println(hql);
    }
}
