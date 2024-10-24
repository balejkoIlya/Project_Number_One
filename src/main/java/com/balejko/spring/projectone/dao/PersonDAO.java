package com.balejko.spring.projectone.dao;

import com.balejko.spring.projectone.models.Book;
import com.balejko.spring.projectone.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class)).stream()
                .findAny().orElse(null);
    }


    public void savePerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person(full_name,year_of_birth) values (?,?)",
                person.getFullName(), person.getYearOfBirth());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET full_name=?,year_of_birth=? WHERE person_id=?",
                person.getFullName(), person.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person where person_id=?", id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }


    public void addBookToPerson(int bookId, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?",personId,bookId);
    }

    public void removeBookFromPerson(int bookId) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?",bookId);
    }

    public Optional<Person> getPersonByFullName(String fullname){
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?",new Object[]{fullname},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}
