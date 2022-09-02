package com.mypro.fruit.servlets;

import com.mypro.fruit.dao.FruitDAO;
import com.mypro.fruit.dao.impl.FruitDAOImpl;
import com.mypro.fruit.pojo.Fruit;
import com.mypro.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");
        //获取参数
        String fidStr = req.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = req.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");
        //执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //资源跳转
        //super.processTemplate("index",req,resp);
        //作用相当于request.getRequestDispatcher("index.html").forward(req,resp);没有进行session的重置，数据未刷新
        //此处需要进行重定向,目的是为了给IndexServlet发请求,以此重新获取fruitList并覆盖到session中
        //这样才能让html界面中显示的数据是最新的.
        resp.sendRedirect("index");
    }
}
