package org.westos.web;

import org.westos.bean.Users;
import org.westos.dao.UserDao;
import org.westos.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login", name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");

            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();

            String input_code = request.getParameter("input_code");
            String check_code = (String) request.getSession().getAttribute("check_code");
            request.getSession().removeAttribute("check_code");
            if (input_code.equalsIgnoreCase(check_code) && username != null && password != null) {
                Users users = new Users();
                users.setUsername(username);
                users.setPassword(password);
                boolean b = new UserServiceImpl().loginUser(users);
                if (b) {
                    String rem = request.getParameter("rem");
                    if (rem != null) {
                        Cookie cookie = new Cookie("username", username);
                        Cookie cookie2 = new Cookie("password", password);
                        cookie.setMaxAge(60 * 60 * 24 * 7);
                        cookie2.setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(cookie);
                        response.addCookie(cookie2);
                    }
                    request.setAttribute("username",username);
                    request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);
                }else{
                    request.setAttribute("msg","用户名或密码错误");
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                }
            }else {
                request.setAttribute("msg","验证码错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
