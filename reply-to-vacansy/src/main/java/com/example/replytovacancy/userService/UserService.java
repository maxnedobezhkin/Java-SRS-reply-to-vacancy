package com.example.replytovacancy.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.replytovacancy.user.User;
import com.example.replytovacancy.userRepository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}
}
