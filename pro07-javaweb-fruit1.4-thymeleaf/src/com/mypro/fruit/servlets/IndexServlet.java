package com.mypro.fruit.servlets;

import com.mypro.fruit.dao.FruitDAO;
import com.mypro.fruit.dao.impl.FruitDAOImpl;
import com.mypro.fruit.pojo.Fruit;
import com.mypro.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//Servlet从3.0支持注解注册方式,不需要再在xml文件添加映射
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList();
        //保存到session作用域
        HttpSession session = request.getSession();
        session.setAttribute("fruitList",fruitList);

        //此处视图名称为 index
        //那么Thymeleaf会将这个逻辑视图名称对应到物理视图名称上去
        //逻辑视图名称： index
        //物理视图名称： prefix + 逻辑视图名称 + suffix
        //真实视图名称：   /    +    index   +  .html
        super.processTemplate("index",request,response);
    }
}
