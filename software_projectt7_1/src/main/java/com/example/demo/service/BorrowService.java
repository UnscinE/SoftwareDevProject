package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Borrow;
import com.example.demo.repo.BorrowRepository;

@Service
public class BorrowService {

	@Autowired
	BorrowRepository borrowRepo;
	
	
	
	public List<Borrow> getBook(){
		List<Borrow> books = (List<Borrow>) borrowRepo.findAll(); 
		return books;
	}
	
	public Borrow getBorrowById(Long id) {
		return borrowRepo.findById(id).orElseThrow();
	}
	
	public Borrow addBorrow(Borrow borrow) {
		borrowRepo.save(borrow);
		return borrow;
	}
	public Borrow updatebook(Long id,Borrow borrow) {
		
		Borrow newborrow = borrowRepo.findById(id).get();
		newborrow.setReturn_date(borrow.getReturn_date());
		newborrow.setBorrow_date(borrow.getBorrow_date());
		return borrowRepo.save(newborrow);
	}
	
	public void deleteByid(Long id) {
		Borrow borrow = borrowRepo.findById(id).orElseThrow();
		
		borrowRepo.delete(borrow);
	}
	
	
}