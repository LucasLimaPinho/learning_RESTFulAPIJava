package com.claro.rest.webservices.test.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	// Versioning aqui com parâmetros tem papel fundamental para entendermos também a utilização de 
	// de query parameters para paginação de APIs. Como implementar 'limit' e 'offset'?
	// Deve ser uma implementação parecida com o versionamento por anotação oferecido pelo 
	// framework Spring Boot.

	
	@GetMapping("/v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Lucas Pinho");
	}
	
	
	@GetMapping("/v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Lucas","Pinho"));
	}

	
	
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Lucas Pinho");
	}
	
	// A chamada aqui é /person/param?version=2
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Lucas","Pinho"));
	}

}
