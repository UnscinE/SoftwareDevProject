package com.example.demo.model;

import java.time.LocalDate;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrow")
public class Borrow {
	 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BR_id")
	private long BR_id;
	private LocalDate borrow_date; 
	private LocalDate return_date;
	
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "bid", nullable = false) 
	private Book book;
	
	public Borrow() {
		super();
		// TODO Auto-generated constructor stub
		
	}
	public Borrow( LocalDate borrow_date, LocalDate return_date , Book book , Account user) {
		super();
		this.borrow_date = borrow_date;
		this.return_date = return_date;
		this.book = book;
		this.account = user;
		
	}
	public long getBR_id() {
		return BR_id;
	}
	public void setBR_id(long bR_id) {
		BR_id = bR_id;
	}
	public LocalDate getBorrow_date() {
		return borrow_date;
	}
	public void setBorrow_date(LocalDate borrow_date) {
		this.borrow_date = borrow_date;
	}
	public LocalDate getReturn_date() {
		return return_date;
	}
	public void setReturn_date(LocalDate return_date) {
		this.return_date = return_date;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
}














