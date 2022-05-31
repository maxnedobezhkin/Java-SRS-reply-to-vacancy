package com.example.replytovacancy.user;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@SequenceGenerator (
	name = "user_name",
	sequenceName = "user_name",
	allocationSize = 1
	)
	
	@GeneratedValue(
	strategy = GenerationType.SEQUENCE,
	generator = "user_name"
			)
	private int id;
	
	private String name;
	private String surname;
	private String contact;
	private Blob resume; 
	private String letter;
	private String resumeName;
	
	public User() {
		super();
	}

	public User(int id, String name, String surname, String contact, String letter) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.contact = contact;
		this.letter = letter;
	}
	
	
	
	public String getResumeName() {
		return resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public Blob getResume() {
		return resume;
	}

	public void setResume(Blob resume) {
		this.resume = resume;
	}

	public User(String name, String surname, String contact, Blob resume, String letter) {
		super();
		this.name = name;
		this.surname = surname;
		this.contact = contact;
		this.resume = resume;
		this.letter = letter;
	}

	public User(String name, String surname, String contact, String letter) {
		super();
		this.name = name;
		this.surname = surname;
		this.contact = contact;
		this.letter = letter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", contact=" + contact + ", letter="
				+ letter + "]";
	}
	
}
