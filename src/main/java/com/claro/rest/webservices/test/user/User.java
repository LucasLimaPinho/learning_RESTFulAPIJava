package com.claro.rest.webservices.test.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {
	
	private Integer id;
	
	// @Size faz parte da dependency spring-boot-starter-validation e seta o mínimo de caractereres que
	// o name em uma request de POST deve possuir. Caso a condição não seja atendida,
	// a API lança 400 Bad Request (HTTP Status)
	
	@Size(min=2, message="Nome deve ter mais do que 02 caracteres")
	private String name;
	
	// @Past faz parte da dependency spring-boot-starter-validation e seta que o birthDate
	// em um request de POST deve estar no passado. Caso a condição não seja atendida,
	// a API lança 400 Bad Request (HTTP Status)
	
	@Past
	private Date birthDate;
	
	// Construtor (Alt + Shift + S)
	
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	// toString() overrride (Alt + Shift + S)
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	// Setters and Getters (Alt + Shift + S)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	

}
