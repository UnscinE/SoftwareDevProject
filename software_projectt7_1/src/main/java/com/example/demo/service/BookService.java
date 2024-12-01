package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repo.BookNotFoundException;
import com.example.demo.repo.BookRepository;

import reactor.core.publisher.Flux;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepo;
	
	public List<Book> getBook(){
		List<Book> books = (List<Book>) bookRepo.findAll(); 
		return books;
	}
	
	public Book getBookById(Long id) {
		return bookRepo.findById(id).orElseThrow(()-> new BookNotFoundException(id));
	}
	
	public Book addBook(Book book) {
		bookRepo.save(book);
		return book;
	}
	
	public Book updatebook(Long id, Book book) {
        Book existingBook = bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setAvailability(book.isAvailability());
        existingBook.setImageUrl(book.getImageUrl());

        // Update Bookdetail if it exists
        if (existingBook.getBookdetail() != null) {
            existingBook.getBookdetail().setIsbn(book.getBookdetail().getIsbn());
            existingBook.getBookdetail().setDescription(book.getBookdetail().getDescription());
            existingBook.getBookdetail().setPublisher(book.getBookdetail().getPublisher());
        }

        // Save the updated book (which also saves the updated Bookdetail)
        return bookRepo.save(existingBook);
    }
	
	public void deleteByid(Long id) {
		Book book = bookRepo.findById(id).orElseThrow(()->new BookNotFoundException(id));
		
		bookRepo.delete(book);
	}
	
	
	
}






























