package ru.elikhanov.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.elikhanov.library.dao.BookDAO;
import ru.elikhanov.library.dao.PersonDAO;
import ru.elikhanov.library.models.Book;
import ru.elikhanov.library.models.Person;
import javax.validation.Valid;
import java.util.Optional;


/**
 * @author Elikhanov
 */

@AllArgsConstructor
@Controller
@RequestMapping("/books")
public class BooksController {

    private BookDAO bookDAO;
    private PersonDAO personDAO;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books",bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person){
        model.addAttribute("book",bookDAO.show(id));

        Optional<Person> bookUser = bookDAO.getUser(id);

        if (bookUser.isPresent()){
            model.addAttribute("user",bookUser.get());
        }
        else
            model.addAttribute("people",personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") @Valid Book book){
        return "books/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book",bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping ("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors()) return "books/edit";
        bookDAO.update(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/deleteBookUser")
    public String deleteBookUser(@PathVariable("id") int id){
        bookDAO.deleteBookUser(id);
        return "redirect:/books/"+id;
    }


    @PatchMapping("/{id}/addBookUser")
    public String addBookUser(@ModelAttribute("person") Person person,
                               @PathVariable("id") int id){
        bookDAO.addBookUser(person,id);
        return "redirect:/books/"+id;
    }

}
