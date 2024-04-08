package com.example.service.impl;

import com.example.entity.book.Book;
import com.example.mapper.BookMapper;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;
    @Override
    public List<Book> searchBookByIsbn(String isbn)   {
        return bookMapper.findBookByIsbn(isbn);
    }

    @Override
    public List<Book> searchBookByName(String name) {
        return bookMapper.findBookByName(name);

    }

    @Override
    public List<Book> searchBookByAuthor(String author) {
        return bookMapper.findBookByAuthor(author);

    }
    @Override
    public List<Book> searchBookByCategory (String category) {
        return bookMapper.findBookByCategory(category);

    }

    @Override
    public List<Book> showAllBooks() {
        return bookMapper.showAllBooks();
    }


    @Override
    public int addBook(Book book) {
       if(bookMapper.addBook(book)>1)
        return 1;
         else return 0;
    }

    @Override
    public int deleteBook(String isbn) {
       if(bookMapper.deleteBook(isbn) >1)
        return 1;
        else return 0;
    }

    @Override
    public int updateBook(Book book) {
        if(bookMapper.updateBook(book)>1)
        return 1;
        else return 0;
    }

    @Override
    public int rentBook(Date rent_date, Date return_date, String isbn) {
        if(bookMapper.rentBook( rent_date,return_date,isbn)>1)
        return 1;
        else return 0;
    }

    @Override
    public int returnBook(String isbn) {
       if(bookMapper.returnBook(isbn)>1)
        return 1;
        else return 0;
    }
}
