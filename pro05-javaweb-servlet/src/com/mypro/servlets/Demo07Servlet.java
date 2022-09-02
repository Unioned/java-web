package com.mypro.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Demo07Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo07 running......");
    }
}

/*6. 服务器内部转发以及客户端重定向
    1） 服务器内部转发 : request.getRequestDispatcher("...").forward(request,response);
      - 一次请求响应的过程，对于客户端而言，内部经过了多少次转发，客户端是不知道的
      - 地址栏没有变化
      状态码status Code:302 Found
    2） 客户端重定向： response.sendRedirect("....");
      - 两次请求响应的过程。客户端肯定知道请求URL有变化
      - 地址栏有变化
      状态码status Code:200 Found
      */
