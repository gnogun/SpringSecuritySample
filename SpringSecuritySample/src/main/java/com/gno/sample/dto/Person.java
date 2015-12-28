package com.gno.sample.dto;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="person")
public class Person {

	private int idx;

	private String name;
	private String email;
	private String password;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public Person(String name, String email, String password) {
		// TODO Auto-generated constructor stub
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name=" + name + ", password=" + password + ", email=" + email;
	}

	
}
