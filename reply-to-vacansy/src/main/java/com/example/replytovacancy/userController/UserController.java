package com.example.replytovacancy.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.replytovacancy.userService.UserService;

@Controller
@RequestMapping(path = "/ctrl")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController (UserService userService) {
		this.userService = userService;
	}

}
