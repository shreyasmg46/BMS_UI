package com.cruds.entity;

public class Book {
	
	private int ISBN;
	private String title;
	private String category;
	private int NoOfBooks;
	private Author author;
	
	
	public Book(int iSBN, String title, String category, int noOfBooks, Author author) {
		super();
		ISBN = iSBN;
		this.title = title;
		this.category = category;
		NoOfBooks = noOfBooks;
		this.author = author;
	}

	public Book(int iSBN) {
		super();
		ISBN = iSBN;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNoOfBooks() {
		return NoOfBooks;
	}

	public void setNoOfBooks(int noOfBooks) {
		NoOfBooks = noOfBooks;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", title=" + title + ", category=" + category + ", NoOfBooks=" + NoOfBooks
				+ ", author=" + author + "]";
	}
	
	
	
	
	

}
