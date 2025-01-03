package com.sprinboot_bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot_bookstore.entity.Book;
import com.sprinboot_bookstore.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bRepo;
	
	public void save(Book b) {
		
		bRepo.save(b);
	}
	public List<Book> getAllBook(){
		return bRepo.findAll();
	}
	
	public Book getBookById(int id) {
		return bRepo.findById(id).get();
	}
	public void deleteById(int id) {
		bRepo.deleteById(id);
		
	}
}
