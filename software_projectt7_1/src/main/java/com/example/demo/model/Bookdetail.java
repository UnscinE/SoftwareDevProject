package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Bookdetail")
public class Bookdetail {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Bdid;
	
	private String isbn;
	private String description;
	private String publisher;
	
	@OneToOne  // Inverse side of the relationship
	private Book book;
	
	public Bookdetail() {
		super();
	}
	
	public Bookdetail(String isbn, String description, String publisher) {
		this.isbn = isbn;
		this.description = description;
		this.publisher = publisher;
	}
	
	// Getters and setters
	public long getBdid() {
		return Bdid;
	}

	public void setBdid(long Bdid) {
		this.Bdid = Bdid;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


}






















