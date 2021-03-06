package com.claro.rest.webservices.test.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
		
		
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		
		// HATEOAS ------------------> Hipermidia As The Engine of Application State (HATEOAS)
		
		// *********** Permite que possamos colocar na response um link para outro método
		// No caso, na resposta para o método retrieveUser iremos colocar o link para retrieveAllUsers por meio de EntityModel da dependency hateoas
		// e também da classe WebMvcLinkBuilder com methodOn.
		// Chamamos o link "href" de "all-users" fazendo relação com o método retrieveAllUsers() da mesma classe ---> this.getClass()
		
		
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}
	
	// POST - criar um novo usuário
	// input - detalhes do novo usuário
	// output - CREATED & retornar também o novo usuário criado. Além de retornar também o URI do novo usuário criado.

		
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		
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
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		User user = service.deleteById(id);
		
		if (user == null)
			throw new UserNotFoundException("id-" + id + " não encontrado");	
					
			
	}

}
