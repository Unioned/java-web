package com.mypro.fruit.servlets;

import com.mypro.fruit.dao.FruitDAO;
import com.mypro.fruit.dao.impl.FruitDAOImpl;
import com.mypro.myssm.myspringmvc.ViewBaseServlet;
import com.mypro.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);

            resp.sendRedirect("index");
        }
    }
}