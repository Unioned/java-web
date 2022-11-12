package come.me.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //get方式不需要编码（基于tomcat8）
        /*如果是在tomcat8之前版本，get转码步骤
        String fname = request.getParameter("fname");
        1.将字符串打散成字节数据
        byte[] bytes = fname.getBytes(charsetName:"ISO-8859-1");
        2.将字节数组按照指定的编码格式重新组装成字符串
        fname = new String(bytes,"UTF-8");
        * */
        //post方式下进行编码设置，防止汉字乱码
        //post的设置编码方式必须要设置在所有获取参数动作之前
        request.setCharacterEncoding("UTF-8");
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        System.out.println("fname = "+fname);
        System.out.println("price = "+price);
        System.out.println("fcount = "+fcount);
        System.out.println("remark = "+remark);
    }
}
