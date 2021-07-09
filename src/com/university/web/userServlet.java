package com.university.web;

import com.sun.net.httpserver.HttpServer;
import com.university.pojo.User;
import com.university.service.impl.UserService;
import com.university.service.impl.UserServiceImpl;
import com.university.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shk
 * @create 2021--04--29--16:05
 */
@WebServlet("/userServlet")
public class userServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("regist");
        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        User user = webUtils.copyParamToBean(req.getParameterMap(), new User());
        System.out.println(user);

        //获取session中的验证码
        String key = (String)req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        //删除session中的验证码
        req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");

        if (key!=null&&code.equalsIgnoreCase(key)){
//        3、检查 用户名是否可用
            if (userService.existUsername(username)) {
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("code", code);
                req.setAttribute("password", password);
                req.setAttribute("email", email);
                System.out.println("用户名[" + username + "]已存在!");
//        跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                System.out.println("1");
                //      可用
//                调用Service保存到数据库
                userService.registUser(user);
//
//        跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
            }
        }
        else{
            //记住用户名和邮箱
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.setAttribute("msg", "验证码错误");
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }



/*//        2、检查 验证码是否正确  === ,要求验证码为:abcde
        if ("abcde".equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existUsername(username)) {
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("code", code);
                req.setAttribute("password", password);
                req.setAttribute("email", email);
                System.out.println("用户名[" + username + "]已存在!");
//        跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                System.out.println("1");
                //      可用
//                调用Service保存到数据库
                userService.registUser(user);
//
//        跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            //记住用户名和邮箱
            req.setAttribute("useranme", username);
            req.setAttribute("email", email);
            req.setAttribute("msg", "验证码错误");
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }*/
    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        {
            System.out.println("login");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //1.检查是否有
            User user = new User(username, password);
            System.out.println(userService.login(user) == null);
            if (userService.login(user) == null) {
                //登录失败
                System.out.println("登录失败");
                request.setAttribute("msg", "用户名或密码错误");
                request.setAttribute("username", username);
        /*    request.setAttribute("username",request.getAttribute("username"));
            request.setAttribute("password",request.getAttribute("password"));*/
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            } else {
                //登录成功
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
            }

        }
    }
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/pages/user/login.jsp");
    }
}
