package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Account;
import com.example.demo.repo.AccountNotFoundException;
import com.example.demo.repo.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepo;
	
	public List<Account> getAccount(){
		List<Account> accounts = (List<Account>) accountRepo.findAll();
		return accounts;
	}
	
	public Account getAccountById(Long id) {
		return accountRepo.findById(id).orElseThrow(()-> new AccountNotFoundException(id));
	}
	
	public Account getAccountByEmail(String email) {
	    return accountRepo.findByEmailAddress(email)
	            .orElseThrow(() -> new AccountNotFoundException("Account not found with email: " + email));
	}

	
	public void save(Account a) {
	    if (accountRepo.findByEmailAddress(a.getEmailAddress()).isPresent()) {
	        throw new RuntimeException("Email already exists");
	    }
	    accountRepo.save(a);
	}
	
	public Account addAccount(Account a) {
		accountRepo.save(a);
		return a;
	}
	
	public void deleteById(Long id) {
		Account account = accountRepo.findById(id).orElseThrow(()-> new AccountNotFoundException(id));
		accountRepo.delete(account);
	}
	
	public Account updateAccount(Long id , Account a) {
		Account existingAccount = accountRepo.findById(id).get();
		existingAccount.setName(a.getName());
		existingAccount.setEmailAddress(a.getEmailAddress());
		existingAccount.setPhoneNumber(a.getPhoneNumber());
		//existingAccount.setRole(a.getRole());
		existingAccount.setPassword(a.getPassword());
		return accountRepo.save(existingAccount);
	}

	public boolean isAdmin(String emailAddress) {
		Optional<Account> account = accountRepo.findByEmailAddress(emailAddress);
		if(account != null) {
			Account getAccount = account.get();
			return getAccount.isAdmin();
		}
		return false;
	}
	
	

}


























