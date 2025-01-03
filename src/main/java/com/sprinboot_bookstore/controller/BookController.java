package com.sprinboot_bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.sprinboot_bookstore.entity.Book;
import com.sprinboot_bookstore.entity.MyBookList;
import com.sprinboot_bookstore.service.BookService;
import com.sprinboot_bookstore.service.MyBookListService;



@Controller
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookService;

    // Home page mapping
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // Book registration page mapping
    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister";
    }

    // My Books page mapping
    @GetMapping("/my_books")
    public String getMyBooks(Model model) {
    	List<MyBookList> list= myBookService.getAllMyBooks();
    	model.addAttribute("book",list);
    	return "myBooks";
    }

    // Available books page mapping
    @GetMapping("/available_books")
    public ModelAndView getAllBook() {
        List<Book> list = service.getAllBook();
        return new ModelAndView("bookList", "book", list);
    }

    // Save book details
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        service.save(b);
        return "redirect:/available_books";
    }

    // Add a book to MyBookList
    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        System.out.println("Received ID: " + id);
        Book b = service.getBookById(id);
        MyBookList mb = new MyBookList(b.getId(), b.getName(), b.getAuthor(), b.getPrice());
        myBookService.saveMyBooks(mb);
        return "redirect:/my_books";
    }
    
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id,Model model) {
    	Book b=service.getBookById(id);
    	model.addAttribute("book" , b);
    	return "bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id,Model model) {
    service.deleteById(id);
    	
    	return "redirect:/available_books";
    }
}
