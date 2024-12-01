package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;

@Repository
public interface BookRepository extends CrudRepository <Book,Long> {
	

}
