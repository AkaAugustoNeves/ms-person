package br.com.pilares.msperson.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pilares.msperson.model.form.PersonForm;
import br.com.pilares.msperson.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	private PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return personService.findAll();
	}
	
	@GetMapping("/{hash}")
	public ResponseEntity<?> findByHash(@PathVariable String hash){
		return personService.findByHash(hash);
	}
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody PersonForm form){
		return personService.register(form);
	}
	
	
}
