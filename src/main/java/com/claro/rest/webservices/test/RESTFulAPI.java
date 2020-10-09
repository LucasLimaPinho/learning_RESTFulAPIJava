package com.claro.rest.webservices.test;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


// Social Media Application

// Retrieve all users  - GET /users
// Create a user - POST /users
// Retrieve one user - GET /users/{id} -> example GET /users/1
// Delete a user - DELETE /users/{id} -> example DELETE /users/1

// Retrieve all posts from a user - GET /users/{id}/posts
// Create a post for a user - POST /users/{id}/posts
// Retrieve details of a post - GET /users/{id}/posts/{post_id}

// * Question to answer:

/*		1. What is dispatcher servlet? 
 * 
 * 
 * 			R: DispatcherServlet está no classpath automaticamente com a 
 * 			configuração do spring-boot-starter-web.Spring-boot autoconfiguration
 * 			faz muito trabalho de configuração de DispatcherServlet e outras classes. 
 * 
 * 
		2. Who is configuring dispatcher servlet? 
		
		
			R: Spring Boot AutoConfiguration.
		
		
		3. What does dispatch servlet do?
		
			R: Dispatcher Servlet faz o handling das requests. A request vai para o DispatcherServlet
			Dispatcher Servlet sabe de todos os mapping diferentes presentes na aplicação. 
			Ele sabe por exemplo que "/helloworld-bean" com método GET está mapeado chamar o método
			HelloWorldController.helloWorldBean()
			
			Dispatcher Servlet olha para a URI e o request method. Mas como saber que tenho que mandar
			uma response? -----> Na anotação @RestController tem uma anotação @ResponseBody que transforma
			o output em algum outro formato (JSON no caso - Jackson2ObjectMapperBuilderCustomizerConfiguration).
			
			
		4. How does the HelloWorldBean object get converted to JSON?
		
				R: Jackson2ObjectMapperBuilderCustomizerConfiguration (Spring Boot autoconfiguration)
				Faz a conversão de JSON para Beans e de Beans para JSON.
				
				
		5. Who is configuring the error mapping?	
		
				R: Spring Boot autoconfiguration.
*/	


@SpringBootApplication
public class RESTFulAPI {

	public static void main(String[] args) {
		SpringApplication.run(RESTFulAPI.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		
		localeResolver.setDefaultLocale(Locale.US);
		
		return localeResolver;
		
		
	}
	
	@Bean	
	public ResourceBundleMessageSource messageSource() {
		
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;		
		
		
	}

}
