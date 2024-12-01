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

import com.example.demo.model.Bookdetail;
import com.example.demo.service.BookDetailService;

@RestController
@RequestMapping("/api")
public class BookdetailController {
	
	@Autowired
	BookDetailService bookdetailService;
	
	@GetMapping("/bookdetails")
	public ResponseEntity<List<Bookdetail>> getBook(){
		List<Bookdetail> bookdetails = bookdetailService.getBookdeatil();
		return new ResponseEntity<>(bookdetails,HttpStatus.OK);
	}
	
	@GetMapping("/bookdetails/{id}")
	  public ResponseEntity<Bookdetail> getAuthorById(@PathVariable("id")
	  Long Id){
		  Bookdetail bookdetail = bookdetailService.getBookdetailById(Id);
		  return new ResponseEntity<>(bookdetail,HttpStatus.OK);
	  }
	  //Post to add a new author
	  @PostMapping("/bookdetails")
	  public Bookdetail addNewBookdetail(@RequestBody Bookdetail bd) {
		  System.out.println(bd);
		 
		  Bookdetail savedBookdetail = bookdetailService.save(bd);
		  return savedBookdetail;
	  }
	  
	  @PutMapping("/bookdetails/{id}") //update
	  public ResponseEntity<Bookdetail> updateBDT(@PathVariable("id") Long Bdid,@RequestBody Bookdetail BDT){
		  Bookdetail updateBDT = bookdetailService.updateBookdetail(Bdid, BDT);
		  return new ResponseEntity<>(updateBDT,HttpStatus.OK);
	  }

	  @DeleteMapping("/authors/{id}")
	    public ResponseEntity<String> deleteBDT(@PathVariable("id") Long id) {
		  bookdetailService.deleteById(id);
	        return ResponseEntity.ok("Bookdetail deleted successfully");
	    }
	}