package org.westos.web;

import org.apache.commons.beanutils.BeanUtils;
import org.westos.bean.Users;
import org.westos.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(value = "/register", name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            Map<String, String[]> map = request.getParameterMap();
            Users users = new Users();
            BeanUtils.populate(users, map);
            String input_code = request.getParameter("input_code");
            String check_code = (String) request.getSession().getAttribute("check_code");
            request.getSession().removeAttribute("check_code");
            if (input_code.equalsIgnoreCase(check_code)) {
                boolean b = new UserServiceImpl().registerUser(users);
                if (b) {
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", "注册失败！");
                    request.getRequestDispatcher("/register.jsp").forward(request, response);
                }
            }else {
                request.setAttribute("cc_error","验证码错误");
                request.getRequestDispatcher("/register.jsp").forward(request,response);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
