package com.mypro.fruit.servlets;

import com.mypro.fruit.dao.FruitDAO;
import com.mypro.fruit.dao.impl.FruitDAOImpl;
import com.mypro.fruit.pojo.Fruit;
import com.mypro.myssm.myspringmvc.ViewBaseServlet;
import com.mypro.myssm.util.StringUtil;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        FruitDAO fruitDAO = new FruitDAOImpl();
        //设置默认值pageNo为1
        Integer pageNo = 1;
        //获取当前会话
        HttpSession session = request.getSession();
        String keyword = null;

        String operate = request.getParameter("operate");
        //如果operate!=null说明是通过表单查询按钮过来的
        //如果operate为龙,说明不是通过表单查询过来的

        if(StringUtil.isNotEmpty(operate) && "search".equals(operate)){
            //说明此处是通过表单查询过来的
            //需要将keyword存储到session中,在之后进行翻页可以从session中进行数据查找,
            //即进行翻页操作不会影响到数据筛选
            //此时将pageNo还原为1,keyword直接从请求参数(即客户输入到文本框的数据)中获取.
            pageNo = 1;
            keyword = request.getParameter("keyword");
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            //将得到的keywoed存储到session中
            session.setAttribute("keyword",keyword);
        }else{
            //说明不是通过表单查询过来的
            //此时keyword应该从session作用域获取

            //得到当前页码
            String pageNoStr = request.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
                //如果读取到pageNo参数中读取到则将pageNoStr强转为pageNo，覆盖掉默认的pageNo=1
            }
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj;
            }else{
                keyword = "";
            }
        }

        //重新更新当前页的值
        session.setAttribute("pageNo",pageNo);

        //包含关键字查询水果记录
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword,pageNo);
        session.setAttribute("fruitList",fruitList);

        //包含关键字记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //包含关键字的最大页数(五条一页)
        /*总记录条数       总页数
             1              1
             5              1
             6              2
             10             2
             11             3
             n          （n+5-1）/5

        * */
        int pageCount = (fruitCount + 5 - 1) / 5;
        session.setAttribute("pageCount",pageCount);


        //此处视图名称为 index
        //那么Thymeleaf会将这个逻辑视图名称对应到物理视图名称上去
        //逻辑视图名称： index
        //物理视图名称： prefix + 逻辑视图名称 + suffix
        //真实视图名称：   /    +    index   +  .html
        //于是最后会转到以/index.html结尾的相关页面
        super.processTemplate("index",request,response);
    }
}
