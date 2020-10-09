package com.claro.rest.webservices.test.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	// método para retornar 'Hello World'
	// GET
	// URI - /helloworld
	
	@RequestMapping(method = RequestMethod.GET, path="/helloworld")
	public String helloWorld() {
		
		return "Hello World - RESTFul API!";
				
	}
	
	// Criar um GetMapping para HelloWOrld retornando um BEAN
	// GetMapping pode ser usado diretamente ao invés de RequestMapping + method como argumento
	
	@GetMapping(path="/helloworld-bean")
	public HelloWorldBean helloWorldBean() {
		
		return new HelloWorldBean("Hello World - RESTFul API! Hello World com Bean.");
				
	}
	
	// Enhancing the Hello World Service with a Path Variable
	
	// /helloworld/path-variable/bananapancakes
	
	@GetMapping(path="/helloworld/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Hello World, %s", name));
				
	}

}
