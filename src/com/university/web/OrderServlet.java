package com.university.web;

import com.university.pojo.Cart;
import com.university.pojo.User;
import com.university.service.impl.OrderService;
import com.university.service.impl.OrderServiceImpl;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shk
 * @create 2021--05--13--19:26
 */

@WebServlet("/orderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获得购物车对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        //2.获得userId

        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser==null){
            req.getSession().setAttribute("mess","您未登录,无法使用结账功能,已为您跳转");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();
        //3.调用orderService.createOrder(cart,UserId)
        String orderId = orderService.createOrder(cart, userId);
        //
        req.getSession().setAttribute("orderId",orderId);
        //请求转发
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");


    }
}
