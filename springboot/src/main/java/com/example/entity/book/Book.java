package com.example.entity.book;
import lombok.Data;

import java.sql.Date;
@Data
public class Book {
    String isbn;
    String name;
    String author;
    boolean status;
    String category;
    Date rent_date;
    Date return_date;
    public Book(){}
    public Book(String name, String author, String description, String image, String category, String isbn, boolean status, Date rent_date, Date return_date) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.status = status;
        this.rent_date = rent_date;
        this.return_date = return_date;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }




    public String getCategory() {
        return category;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isStatus() {
        return status;
    }

    


    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }





    public void setCategory(String category) {
        this.category = category;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setRent_date(Date rent_date) {
        this.rent_date = rent_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", isbn='" + isbn + '\'' +
                ", status=" + status +
                ", rent_date='" + rent_date + '\'' +
                ", return_date='" + return_date + '\'' +
                '}';
    }

    public Date getRent_date() {
        return rent_date;
    }

    public Date getReturn_date() {
        return return_date;
    }
}
