package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	Optional<Account> findByEmailAddress(String emailAddress);
}
