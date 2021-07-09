package com.university.test;

import com.university.dao.impl.BookDao;
import com.university.dao.impl.BookDaoImpl;
import com.university.pojo.Book;
import com.university.pojo.Page;
import com.university.service.impl.BookService;
import com.university.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author shk
 * @create 2021--05--05--19:04
 */
public class BookDaoTest {
  private BookDao dao=new BookDaoImpl();
  BookService bookService=new BookServiceImpl();

    @Test
    public void addBook() {
        dao.addBook(new Book(null,"亿万富翁","陈威",new BigDecimal(9999),10000,0,null));

    }

    @Test
    public void deleteBookById() {
      dao.deleteBookById(20);
    }

    @Test
    public void updateBook() {
        dao.updateBook(new Book(26,"亿万富翁","陈威",new BigDecimal(9999),10000,1,null));
    }

    @Test
    public void queryBookById() {
      System.out.println(dao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
      List<Book> books = dao.queryBooks();
      for (Book m:books
           ) {
        System.out.println(m);
      }
    }
  @Test
  public void queryForPageTotalCount() {
    System.out.println(dao.queryForPageTotalCount());
  }
  @Test
  public void queryForPageItems() {
    List<Book> books = dao.queryForPageItems(2, Page.PAGE_SIZE);
    System.out.println(books);
  }
  @Test
  public void page(){
    System.out.println(bookService.page(1,4));
  }
  @Test
  public void queryForPageTotalCountByPrice() {
    System.out.println(dao.queryForPageTotalCountByPrice(0,9999));
  }
  @Test
  public void queryForPageItemsByPrice() {
    List<Book> books = dao.queryForPageItemsByPrice(2, Page.PAGE_SIZE,0,9999);
    System.out.println(books);
  }
}