package com.claro.rest.webservices.test.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 5;
	
	static {
		
		users.add(new User(1, "Leo DiCaprio", new Date()));
		users.add(new User(2, "Robert DeNiro", new Date()));
		users.add(new User(3, "Adam Driver", new Date()));
		users.add(new User(4, "Guillermo Del Toro", new Date()));
		users.add(new User(5, "Ryan Gosling", new Date()));
		
	}
	
	public List<User> findAll() {
		
		return users;
		
	}
	
	public User saveUser(User user) {
		
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
		
	}
	
	public User findOne(int id) {
		
		for (User user : users) {
			
			if (user.getId() == id) {
				
				return user;
			}
		}
		
		return null;
	}
}
