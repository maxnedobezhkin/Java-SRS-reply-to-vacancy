package com.example.replytovacancy.userform;

import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

public class UserForm {
	
	private String name;
	private String surname;
	private String contact;
	private MultipartFile resume;
	private String letter = "Добрый день! Прошу рассмотреть мое резюме на должность Backend разработчика";
	
	public MultipartFile getResume() {
		return resume;
	}
	public void setResume(MultipartFile resume) {
		this.resume = resume;
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
	
}
