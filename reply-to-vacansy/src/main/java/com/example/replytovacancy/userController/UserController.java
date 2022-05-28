package com.example.replytovacancy.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.replytovacancy.user.User;
import com.example.replytovacancy.userForm.UserForm;
import com.example.replytovacancy.userService.UserService;

@Controller
public class UserController {
	
	private final UserService userService;
	
	private String errorNameMessage;
	private String errorSurnameMessage;
	private String errorContactMessage;
	private String errorLetterMessage;
	
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
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.POST)
	public String addUser(Model model, @ModelAttribute("userForm") UserForm userForm) {
		
		String name = userForm.getName();
		String surname = userForm.getSurname();
		String contact = userForm.getContact();
		String letter = userForm.getLetter();
		
		if (name != null && name.length() > 0) {
			User newUser = new User(name, surname, contact, letter);
			userService.addUser(newUser);
			return "redirect:/index";
		} else {
			errorNameMessage = "Имя не должно быть пустым";
			model.addAttribute("errorNameMessage", errorNameMessage);
		}
		
		
		return "index";
	}

}
