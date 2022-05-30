package com.example.replytovacancy.usercontroller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.example.replytovacancy.user.User;
import com.example.replytovacancy.userform.UserForm;
import com.example.replytovacancy.userservice.UserService;

@Controller
public class UserController {
	
	private final UserService userService;
	
	private String errorNameMessage;
	private String errorSurnameMessage;
	private String errorContactMessage;
	private String errorResumeMessage;
	private String errorLetterMessage;
	private String errorReplyMessage;
	private String noDataMessage;
	private String successMessage;
	
	private static List<User> users = new ArrayList<User>();
	
	@Autowired
	public UserController (UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String showIndexPage(Model model) {
		
		UserForm userForm = new UserForm();
		model.addAttribute("userForm", userForm);

		return "index";
	}
	
	@RequestMapping(value = "/showAll", method = RequestMethod.GET)
	public String showUsers(Model model) {
		
		
		users.addAll(userService.findAll());
		if (users.isEmpty()) {
			noDataMessage = "В базе нет откликов";
			model.addAttribute("noDataMessage", noDataMessage);
		} else {
			model.addAttribute("users", users);
		}
		

		return "showAll";
	}
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.POST)
	public String addUser(Model model, @ModelAttribute("userForm") UserForm userForm) 
			throws SerialException, SQLException, IOException {
		
		String name = userForm.getName();
		String surname = userForm.getSurname();
		String contact = userForm.getContact();
		String letter = userForm.getLetter();
		MultipartFile resumeTemp = userForm.getResume();
		Blob resume = new SerialBlob(resumeTemp.getBytes());
		
		int countSuccessValidation = 0;
		
		switch (userService.checkName(name)) {
		case 1:
			errorNameMessage = "Имя не может быть пустым";
			model.addAttribute("errorNameMessage", errorNameMessage);
			break;
		case 2:
			errorNameMessage = "Имя содержит недопустимые символы";
			model.addAttribute("errorNameMessage", errorNameMessage);
			break;
		case 0:
			countSuccessValidation++;
			break;
		default:
			break;
		}
		
		switch (userService.checkName(surname)) {
		case 1:
			errorSurnameMessage = "Фамилия не может быть пустой";
			model.addAttribute("errorSurnameMessage", errorSurnameMessage);
			break;
		case 2:
			errorSurnameMessage = "Фамилия содержит недопустимые символы";
			model.addAttribute("errorSurnameMessage", errorSurnameMessage);
			break;
		case 0:
			countSuccessValidation++;
			break;
		default:
			break;
		}
		
		switch (userService.checkContact(contact)) {
		case 1:
			errorContactMessage = "Введите email или телефон";
			model.addAttribute("errorContactMessage", errorContactMessage);
			break;
		case 2:
			errorContactMessage = "Это значение уже используется";
			model.addAttribute("errorContactMessage", errorContactMessage);
			break;
		case 3:
			errorContactMessage = "Проверьте правильность введенного email или телефона";
			model.addAttribute("errorContactMessage", errorContactMessage);
			break;
		
		case 0:
			countSuccessValidation++;
			break;
		default:
			break;
		}
		
		if (resumeTemp.isEmpty()) {
			errorResumeMessage = "Вы не приложили резюме";
			model.addAttribute("errorResumeMessage", errorResumeMessage);
		} else {
			countSuccessValidation++;
		}
		
		switch (userService.checkLetter(letter)) {
		case 1:
			errorLetterMessage = "Поле не должно быть пустым";
			model.addAttribute("errorLetterMessage", errorLetterMessage);
			break;
		case 0:
			countSuccessValidation++;
			break;
		default:
			break;
		}
		
		if (countSuccessValidation == 5) {	
			User newUser = new User(name, surname, contact, resume, letter);
			userService.addUser(newUser);
			userForm.setName("");
			userForm.setSurname("");
			userForm.setContact("");
			userForm.setLetter("");
			userForm.setResume(null);
			successMessage = "Отклик на вакансию Java backend разработчик успешно отправлен";
			model.addAttribute("successMessage", successMessage);
		} else {
			errorReplyMessage = "Не удалось откликнуться на вакансию";
			model.addAttribute("errorReplyMessage", errorReplyMessage);
		}
		return "index";
	}

}
