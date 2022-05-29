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
	
	public int checkName(String name) {
		int result = 0;
		
		if (name == null || name.length() == 0) {
			result = 1;
		} else if (!name.matches("[\\p{L}| ]+")) {
			result = 2;
		}
		return result;
	}
	
	public int checkContact(String contact) {
		int result = 0;
		String emailPattern = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		if (contact == null || contact.length() == 0) {
			result = 1;
		} else {
			if (!userRepository.findByContact(contact).isEmpty()) {
				result = 2;
			} else {
				if (!contact.matches("[\\d]+")) {
					if (!contact.matches(emailPattern)) {
						result = 3;
					}
				}
			}
		}
		return result;
	}
	
	public int checkLetter(String letter) {
		int result = 0;
		if (letter == null || letter.length() == 0) {
			result = 1;
		}
		return result;
	}
}
