package com.example.service;

import com.example.entity.book.Book;

import java.sql.Date;
import java.util.List;

public interface BookService {
    List<Book> searchBookByIsbn(String isbn);
    List<Book> searchBookByName(String name);
    List<Book> searchBookByAuthor(String author);
    List<Book> searchBookByCategory (String category);
    List<Book> showAllBooks();

    int addBook(Book book);
    int deleteBook(String isbn);
    int updateBook(Book book);
    int rentBook(Date rent_date, Date return_date, String isbn);
    int returnBook(String isbn);

}
