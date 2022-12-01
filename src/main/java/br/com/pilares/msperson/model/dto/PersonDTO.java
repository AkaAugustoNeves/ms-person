package br.com.pilares.msperson.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.pilares.msperson.model.entity.Person;

public class PersonDTO {

	private String hash;
	private String name;
	private String email;
	
	public static List<PersonDTO> convert(List<Person> persons){
		return persons.stream().map(PersonDTO::new).collect(Collectors.toList());
	}
	
	public PersonDTO(Person person) {
		this.hash = person.getHash();
		this.name = person.getName();
		this.email = person.getEmail();
	}
	
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
