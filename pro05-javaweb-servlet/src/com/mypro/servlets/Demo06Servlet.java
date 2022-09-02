package com.mypro.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//演示服务器内部转发以及客户端重定向
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo06 running......");
        //服务器端内部转发,地址栏不变动
        req.getRequestDispatcher("demo07").forward(req,resp);
        //客户端重定向
        //resp.sendRedirect("demo07");
    }
}
