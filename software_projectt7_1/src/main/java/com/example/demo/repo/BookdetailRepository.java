package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;
import com.example.demo.model.Bookdetail;

@Repository
public interface BookdetailRepository extends CrudRepository <Bookdetail,Long> {
	

}