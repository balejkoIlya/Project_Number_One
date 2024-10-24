package com.balejko.spring.projectone.controllers;

import com.balejko.spring.projectone.dao.BookDAO;
import com.balejko.spring.projectone.dao.PersonDAO;
import com.balejko.spring.projectone.models.Book;
import com.balejko.spring.projectone.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.getAllBooks());
        return "books/index";
    }

    @GetMapping("{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.getBookById(id));
        model.addAttribute("people", personDAO.getAllPeople());
        model.addAttribute("person1", bookDAO.getPersonByBookId(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBookForm(Model model, Book book) {
        model.addAttribute("book", book);
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book")@Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editFrom(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book")@Valid Book book,
                             BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }


    @DeleteMapping("{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteById(id);
        return "redirect:/books";
    }

    @PatchMapping("{id}/add")
    public String addBookToPerson(@PathVariable("id") int bookId, @ModelAttribute("person") Person person) {
        personDAO.addBookToPerson(bookId, person.getPerson_id());
        return "redirect:/books/"+bookId;
    }

    @PatchMapping("{id}/remove")
    public String removeBookFromPerson(@PathVariable("id")int bookId){
        personDAO.removeBookFromPerson(bookId);
        return "redirect:/books/"+bookId;
    }



}
