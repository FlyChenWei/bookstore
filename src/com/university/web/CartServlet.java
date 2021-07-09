package com.university.web;

import com.university.pojo.Book;
import com.university.pojo.Cart;
import com.university.pojo.CartItem;
import com.university.service.impl.BookService;
import com.university.service.impl.BookServiceImpl;
import com.university.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shk
 * @create 2021--05--12--9:13
 */
@WebServlet("/cartServlet")
public class CartServlet extends BaseServlet {
    private BookService service = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //思路分析
        //1.获取请求的参数,商品编号
        //2.调用bookService.queryBookById(id):Book得到图书的信息
        //3.把图书信息,转换为bookItem商品项
        //4.调用Cart.addItem(cartItem):添加商品项
        //5.重定向返回商品列表
        int id = webUtils.parseInt(req.getParameter("bookId"), 0);
        Book book = service.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(),
                1, book.getPrice(), book.getPrice());
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        String referer = req.getHeader("Referer");
        //最后一个保存进入的数据 保存进session中进行回显
        req.getSession().setAttribute("lastName",cartItem.getName());
        resp.sendRedirect(referer);

    }

    /**
     * 删除购物项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("delete方法");
        System.out.println(req.getParameter("bookId"));
        //思路分析
        //1.获得请求的参数 bookId
        int id = webUtils.parseInt(req.getParameter("bookId"), 0);
        //2.获得session域中的cart对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        //3.删除cart中 id为bookId的项
        cart.deleteItem(id);
        //4.将修改的cart保存进session域中
        req.getSession().setAttribute("cart",cart);
        System.out.println(cart);
        //.请求重定向到
       resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
       //req.getRequestDispatcher("/pages/cart/cart.jsp").forward(req,resp);

    }

    /**
     * 清空购物车
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.清空session中的键为cart的数据
        req.getSession().setAttribute("cart",null);
          //最后一个添加的元素也清空
        req.getSession().setAttribute("lastName",null);
        //2.重定向到cart.jsp页面
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }


    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取请求的参数 商品编号 、商品数量
        int id = webUtils.parseInt(req.getParameter("id"),0);
        int count = webUtils.parseInt(req.getParameter("count"), 1);
        // 获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            // 修改商品数量
            cart.updateCount(id,count);
            // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

}
