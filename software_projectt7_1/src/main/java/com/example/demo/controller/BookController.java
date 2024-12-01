package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.model.Bookdetail;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	BookService bookService;

	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBook(){
		List<Book> books = bookService.getBook();
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	 @GetMapping("/books/{id}")
	public ResponseEntity<Book> getbyid(@PathVariable Long id){
		 Book book = bookService.getBookById(id);
		  return new ResponseEntity<>(book,HttpStatus.OK);
	}

	
	@PostMapping("/books")
	public Book addNewBook(@ModelAttribute Book book) {
		Book savedBook = bookService.addBook(book);
		return savedBook;
	}
	
	@PutMapping("/updatebook/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") Long id,@RequestBody Book book){
		Book updateBook = bookService.updatebook(id, book);
		return new ResponseEntity<>(updateBook, HttpStatus.OK);
	}
	
	 @DeleteMapping("/books/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		 bookService.deleteByid(id);
			return ResponseEntity.ok("Book deleted successfully");
		}
	

}
