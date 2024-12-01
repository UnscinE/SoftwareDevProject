package com.example.demo.repo;

public class BookNotFoundException extends RuntimeException {

	 public BookNotFoundException (Long id) {
	    super("Could not find book " + id);
	  }

}

