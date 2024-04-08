package com.example.mapper;

import com.example.entity.book.Book;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from db_book where name = #{%name%} ")
    List<Book> findBookByName(String name);
    @Select("select * from db_book where  author = #{%author%} ")
    List<Book> findBookByAuthor(String author);
    @Select("select * from db_book where  category = #{%category%} ")
    List<Book> findBookByCategory(String category);
    @Select("select * from db_book where  isbn = #{%isbn%} ")
    List<Book> findBookByIsbn(String isbn);
    @Select("select * from db_book")
    List<Book> showAllBooks();

    @Insert("insert into db_book (isbn,name,author,status,category) values (#{isbn},#{name}, #{author},#{status},#{category})")
    int addBook(Book book);
    @Delete("delete from db_book where isbn = #{isbn}")
    int deleteBook(String isbn);
    @Update("update db_book set name = #{name},author = #{author},status = #{status},category = #{category}  where isbn = #{isbn}")
    int updateBook(Book book);

    @Update("update db_book set rent_date = DATE #{rent_date},return_date = DATE #{return_date},status = 1 where isbn = #{isbn}")
    int rentBook(Date rent_date,Date return_date,String isbn);
    @Update("update db_book set status = 0 where  isbn = #{isbn}")
    int returnBook(String isbn);

}
