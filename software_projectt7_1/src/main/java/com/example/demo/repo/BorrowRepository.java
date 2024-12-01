package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Borrow;

@Repository
public interface BorrowRepository extends CrudRepository <Borrow,Long> {
	

}
