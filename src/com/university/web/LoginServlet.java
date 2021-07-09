package com.university.web;

import com.university.pojo.User;
import com.university.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shk
 * @create 2021--04--18--14:21
 */
public class LoginServlet extends HttpServlet {
    private UserServiceImpl impl=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //1.检查是否有
        User user = new User(username,password);
        if(impl.login(user)==null){
            //登录失败
            request.setAttribute("msg","用户名或密码错误");
            request.setAttribute("username",username);
        /*    request.setAttribute("username",request.getAttribute("username"));
            request.setAttribute("password",request.getAttribute("password"));*/
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            request.getSession().setAttribute("userId",user.getId());
            //登录成功
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
