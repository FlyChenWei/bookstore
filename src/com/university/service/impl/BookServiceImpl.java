package com.university.service.impl;

import com.university.dao.impl.BookDao;
import com.university.dao.impl.BookDaoImpl;
import com.university.pojo.Book;
import com.university.pojo.Page;

import java.util.List;

/**
 * @author shk
 * @create 2021--05--05--19:27
 */
public class BookServiceImpl implements BookService {
    private BookDao dao =new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        dao.addBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
dao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
dao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
       return dao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
       return dao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page=new Page<>();
        //设置页面显示的数据数
        page.setPageSize(pageSize);
        //设置总记录数
        Integer pageTotalCount=dao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal=(pageTotalCount/pageSize>0)?pageTotalCount/pageSize+1:pageTotalCount/pageSize;
        page.setPageTotal(pageTotal);
        //显示的页面编号
        page.setPageNo(pageNo);
        //设置当前页数据
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=dao.queryForPageItems(begin,pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int min, int max, int pageNo, int pageSize) {
        Page<Book> page=new Page<>();
        //设置页面显示的数据数
        page.setPageSize(pageSize);
        //设置总记录数
        Integer pageTotalCount=dao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal=(pageTotalCount/pageSize>0)?pageTotalCount/pageSize+1:pageTotalCount/pageSize;
        page.setPageTotal(pageTotal);
        //显示的页面编号
        page.setPageNo(pageNo);
        //设置当前页数据
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=dao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);
        return page;
    }

    @Override
    public String  toString() {
        return "BookServiceImpl{" +
                "dao=" + dao +
                '}';
    }
}
