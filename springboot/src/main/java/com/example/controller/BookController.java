package com.example.controller;

import com.example.entity.book.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService service;


    @PostMapping("/add")
    public int addBook(@RequestBody Book book) {
        return service.addBook(book);
    }
    @PostMapping("/delete")
    public int deleteBook(@RequestParam(value = "isbn") String isbn) {
        return service.deleteBook(isbn);

    }
    @PostMapping("/update")
    public int updateBook(@RequestBody Book book) {
        return service.updateBook(book);

    }
    @GetMapping("/show")
    public List<Book> showAllBooks() {
        return service.showAllBooks();
    }
    @GetMapping("/searchIsbn")
    public List<Book> searchBookByIsbn(
                             @RequestParam(value = "isbn") String isbn) {
        return service.searchBookByIsbn(isbn);
    }
    @GetMapping("/searchName")
    public List<Book>  searchBookByName(
                             @RequestParam("name") String name) {
        return service.searchBookByName(name);
    }
    @GetMapping("/searchAuthor")
    public List<Book>searchBookByAuthor(
                             @RequestParam("author") String author) {
        return service.searchBookByAuthor(author);

    }
    @GetMapping("/searchCategory")
    public List<Book>searchBookByCategory(
            @RequestParam("category") String category) {
        return service.searchBookByCategory(category);

    }
    @GetMapping("/rent")
    public int rentBook(@RequestParam(value = "rent_date") Date rent_date
                        ,@RequestParam(value = "return_date") Date return_date,
                         @RequestParam(value = "isbn") String isbn) {
        return service.rentBook(rent_date,return_date,isbn);

    }
    @GetMapping("/return")
    public int returnBook(@RequestParam(value = "isbn") String isbn) {
        return service.returnBook(isbn);

    }


}
