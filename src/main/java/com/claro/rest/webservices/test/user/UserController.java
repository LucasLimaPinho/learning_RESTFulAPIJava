package com.claro.rest.webservices.test.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claro.rest.webservices.test.exception.UserNotFoundException;

@RestController
public class UserController {
	
	@Autowired
	private UserDAOService service;
	
	
	// GET /users
	// retrieve All users
	
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		
		return service.findAll();
	}
	
	// GET /users/{id}
	// retrieve User(int id)	
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		
		User user = service.findOne(id);
		
		if (user == null) 
			throw new UserNotFoundException("id-" + id + " não encontrado");

		return user;
		
	}
	
	// POST - criar um novo usuário
	// input - detalhes do novo usuário
	// output - CREATED & retornar também o novo usuário criado. Além de retornar também o URI do novo usuário criado.
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		
		User savedUser = service.saveUser(user);
		
		// ResponseEntity é parte do Spring Framework.
		// Passar o URI do recurso criado
		// URI do recurso criado ----> /users/user.getId()
		
		// .path irá fazer um append no ServletUriComponentsBuilder.fromCurrentRequest() (/users) com o {id} do recurso criado.
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId())
		.toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}

}
