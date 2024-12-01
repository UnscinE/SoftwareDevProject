package com.example.demo.repo;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long id) {
        super("Account not found with ID: " + id);
    }
    
    public AccountNotFoundException(String message) {
        super(message);
    }

}


