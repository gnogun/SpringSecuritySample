package com.gno.sample.dto;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="person")
public class Person {

	private int idx;

	private String id;
	private String email;
	private String password;
	private String author;
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public Person(String id, String email, String password) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id=" + id + ", password=" + password + ", email=" + email;
	}

	
}
