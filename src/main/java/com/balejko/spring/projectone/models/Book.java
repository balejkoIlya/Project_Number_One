package com.balejko.spring.projectone.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int book_id;
    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 2,max = 100,message = "Назвние должно содержать от 2 до 100 символов")
    private String name;
    @NotEmpty(message = "Имя автора не может быть пустым")
    @Size(min = 2,max = 200,message = "Имя автора должно содержать от 2 до 200 символов")
    private String author;
    @Min(value = 0,message = "Год должен быть положетильным")
    private int year;

    public Book() {
    }

    public Book(int book_id, String name, String author, int year) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
