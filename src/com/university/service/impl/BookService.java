package com.university.service.impl;

import com.university.pojo.Book;
import com.university.pojo.Page;

import java.util.List;

/**
 * @author shk
 * @create 2021--05--05--19:24
 */
public interface BookService {
    public void addBook(Book book);
    public void deleteBook(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int min, int max, int pageNo, int pageSize);
}
