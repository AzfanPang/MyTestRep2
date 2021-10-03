package org.westos.web;

import org.westos.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(value = "/checkUname", name = "CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String username = request.getParameter("uname");
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            boolean b = userDao.checkUsername(username);
            if(b){
                String str="{\"msg\":\"yes\",\"code\":\"200\",\"text\":\"用户名可以注册\"}";
                writer.write(str);
            }else{
                String str="{\"msg\":\"no\",\"code\":\"500\",\"text\":\"用户名已存在\"}";
                writer.write(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
