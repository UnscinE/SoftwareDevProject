package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api")
public class AccountController {
	@Autowired
	AccountService accountService;

	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAllAccount(@PathVariable("id") Long Id){
		List<Account> accounts = accountService.getAccount();
		return new ResponseEntity<>(accounts,HttpStatus.OK);
	}
	
	@GetMapping("/accounts/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable("id") Long Id){
		Account accounts = accountService.getAccountById(Id);
		return new ResponseEntity<>(accounts,HttpStatus.OK);
	}
	

	@PostMapping("/accounts")
	public Account addNewAccount(@RequestBody Account account) {
		Account savedAddAcc = accountService.addAccount(account);
		return savedAddAcc;
	}
	
	@PutMapping("/accounts/{id}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account newAcc , @PathVariable Long id){
		Account updateAccount = accountService.updateAccount(id, newAcc);
		return  ResponseEntity.ok(updateAccount);
	}
	
	 @DeleteMapping("/accounts/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		accountService.deleteById(id);
		return ResponseEntity.ok("Account deleted successfully");
	}
	
	
	
	
}
