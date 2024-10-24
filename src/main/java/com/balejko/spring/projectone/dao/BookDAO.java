package com.balejko.spring.projectone.dao;

import com.balejko.spring.projectone.models.Book;
import com.balejko.spring.projectone.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks(){
        return jdbcTemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }
    public Book getBookById(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",
                new Object[]{id},new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void saveBook(Book book){
        jdbcTemplate.update("INSERT INTO Book(name,author,year) values(?,?,?)", book.getName(),
                book.getAuthor(),book.getYear());
    }
    public void deleteById(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?",id);
    }
    public void updateBook(int id,Book book){
        jdbcTemplate.update("UPDATE Book SET name=?,author=?,year=? WHERE book_id=?",
                book.getName(),book.getAuthor(),book.getYear(),id);
    }

    public Person getPersonByBookId(int id){
        return jdbcTemplate.query("SELECT p.* FROM Book b " +
                "JOIN Person p on p.person_id=b.person_id" +
                " WHERE book_id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).
                stream().
                findAny().
                orElse(null);
    }


}
