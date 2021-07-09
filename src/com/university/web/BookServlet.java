package com.university.web;

import com.university.pojo.Book;
import com.university.pojo.Page;
import com.university.service.impl.BookService;
import com.university.service.impl.BookServiceImpl;
import com.university.utils.webUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author shk
 * @create 2021--05--05--19:42
 */
public class BookServlet extends BaseServlet {
    private BookService service = new BookServiceImpl();


    protected void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request.getParameter("pageNo"));
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"), 1);
        pageNo+=1;
        //1.获取请求的参数
        Book book = webUtils.copyParamToBean(request.getParameterMap(), new Book());
        //2.将book添加到数据库中
        service.addBook(book);
        //3.跳转到图书列表页面
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数id
      int id=webUtils.parseInt(request.getParameter("id"),0);
        //2.调用bookService.deleteBookById()
        service.deleteBook(id);
        //3.请求重定向到request.getContextPath()+"/manager/bookServlet?action=list
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }



    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过bookService查询全部图书
        //2.把全部图书保存到request域中
        //3.请求转发到/pages/manager/book_manager.jsp中
        List<Book> books = service.queryBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数图书编号
        //2.调用bookService.QueryBookById()查询图书
        String id = request.getParameter("id");

        int i=webUtils.parseInt(id,0);
        Book book = service.queryBookById(i);
        System.out.println(book);
        //3.保存图书信息到request域中
        request.setAttribute("book", book);
        //4.请求转发到pages/manager/book_edit.jsp页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp?pageNo="+request.getParameter("pageNo")).forward(request,response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过bookService查询所有图书信息
        Book book = webUtils.copyParamToBean(request.getParameterMap(), new Book());
        service.updateBook(book);
        System.out.println("update "+request.getParameter("pageNo"));
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page&pageNo="+request.getParameter("pageNo"));
        //2.查询到的信息保存到request域中
        //3.请求重定向到
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize  设置默认
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用BookService.page(pageNo,pageSize):page对象
        Page<Book> page=service.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3.保存page对象到request域中
        request.setAttribute("page",page);
        //4.请求转发到pages/manager/book_manger.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }
}
