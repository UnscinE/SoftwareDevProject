package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.model.Borrow;
import com.example.demo.service.BorrowService;

@RestController
@RequestMapping("/api")
public class BorrowController {
	
	@Autowired
	BorrowService borrowService;
	
	@GetMapping("/borrows")
	public ResponseEntity<List<Borrow>> getBorrow(){
		List<Borrow> borrows = borrowService.getBook();
		return new ResponseEntity<>(borrows,HttpStatus.OK);
	}
	
	@PostMapping("/borrows")
	public Borrow addNewBook(@RequestBody Borrow borrow) {
		Borrow savedBorrow = borrowService.addBorrow(borrow);
		return savedBorrow;
	}
	
	@PutMapping("/borrows/{id}")
	public ResponseEntity<Borrow> updateBorrow(@PathVariable("id") Long id,@RequestBody Borrow borrow){
		Borrow updateBorrow = borrowService.updatebook(id, borrow);
		return new ResponseEntity<>(updateBorrow, HttpStatus.OK);
	}
	
	@DeleteMapping("/borrows/{id}")
	public ResponseEntity<String> deleteBorrow(@PathVariable Long id){
		borrowService.deleteByid(id);
		return ResponseEntity.ok("Borrow deleted successfully");
	}
	
	

}
