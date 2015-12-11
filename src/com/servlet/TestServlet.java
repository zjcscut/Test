package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * created by IntelliJ IDEA
 *
 * @author zjc
 * @time 2015/11/30-13:24
 */
public class TestServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("hello");

        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        out.println("<h>" + "hello,silly" + "</h>");
    }
}
