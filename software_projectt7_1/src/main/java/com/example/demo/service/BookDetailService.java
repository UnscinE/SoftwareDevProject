package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repo.BookdetailRepository;
import com.example.demo.model.Bookdetail;

@Service
public class BookDetailService {
	@Autowired
	BookdetailRepository bookdetailRepo;
	
	public List<Bookdetail> getBookdeatil(){
		List<Bookdetail> bookdetails = (List<Bookdetail>) bookdetailRepo.findAll(); 
		return bookdetails;
	}
	
	public Bookdetail getBookdetailById(Long id) {
		return bookdetailRepo.findById(id).get();
	}
	
	public Bookdetail save(Bookdetail bookdetail) {
		 bookdetailRepo.save(bookdetail);
		 return bookdetail;
	}
	
	public void deleteById(Long id) {
		Bookdetail bd = bookdetailRepo.findById(id).get();
		bookdetailRepo.delete(bd);
		
	}
	
	public Bookdetail updateBookdetail(Long id , Bookdetail bd) {
		Bookdetail existingBook = bookdetailRepo.findById(id).get();
		existingBook.setDescription(bd.getDescription());
		existingBook.setIsbn(bd.getIsbn());
		existingBook.setPublisher(bd.getPublisher());
		return bookdetailRepo.save(existingBook);
	}
	
	
}
