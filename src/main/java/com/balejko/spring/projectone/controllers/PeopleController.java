package com.balejko.spring.projectone.controllers;

import com.balejko.spring.projectone.dao.PersonDAO;
import com.balejko.spring.projectone.models.Person;
import com.balejko.spring.projectone.utils.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final PersonValidator validator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator validator) {
        this.personDAO = personDAO;
        this.validator = validator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.getAllPeople());
        return "people/index";
    }

    @GetMapping("{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonById(id));
        model.addAttribute("books",personDAO.getBooksByPersonId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute("person")Person person){
        return "people/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("person")@Valid Person person,BindingResult bindingResult){
        validator.validate(person,bindingResult);
        if(bindingResult.hasErrors()){
            return "people/new";
        }
        personDAO.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editForm(Model model,@PathVariable("id")int id){
        model.addAttribute("person",personDAO.getPersonById(id));
        return "people/edit";
    }

    @PatchMapping("{id}")
    public String edit(@ModelAttribute("person")@Valid Person updatedPerson,
                       BindingResult bindingResult
                       ,@PathVariable("id")int id){
        validator.validate(updatedPerson,bindingResult);
        if(bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id,updatedPerson);
        return "redirect:/people";
    }


    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
