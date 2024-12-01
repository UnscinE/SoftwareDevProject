package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="BookPJ")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bid")
	private long bid;
	
	private String title;
	private String author;
	// Add a new variable to store the image URL
    private String imageUrl;
	private boolean availability = true;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bookdetail_id")  // Owning side of the relationship
	private Bookdetail bookdetail;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Borrow> borrow;
	
	public Book() {
		super();
	}
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.availability = true;
	}
	
	// Getters and setters
	public long getBid() {
		return bid;
	}

	public void setBid(long bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}
	
	public String getImageUrl() {
        return imageUrl;  // Getter for the image URL
    }
	
	public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;  // Setter for the image URL
    }

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Bookdetail getBookdetail() {
		return bookdetail;
	}

	public void setBookdetail(Bookdetail bookdetail) {
		this.bookdetail = bookdetail;
	}

	public List<Borrow> getBorrow() {
		return borrow;
	}

	public void setBorrow(List<Borrow> borrow) {
		this.borrow = borrow;
	}
}




























