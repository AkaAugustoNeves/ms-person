package br.com.pilares.msperson.service;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pilares.msperson.model.dto.PersonDTO;
import br.com.pilares.msperson.model.entity.Person;
import br.com.pilares.msperson.model.form.PersonForm;
import br.com.pilares.msperson.repositories.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;
	private RabbitTemplate rabbitTemplate;
	
	public PersonService(PersonRepository personRepository, RabbitTemplate rabbitTemplate) {
		this.personRepository = personRepository;
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public ResponseEntity<PersonDTO> register(PersonForm form) {
		Person person = personRepository.save(new Person(form));
		rabbitTemplate.convertAndSend("NEW_PERSON", "", person);
		return ResponseEntity.status(HttpStatus.CREATED).body(new PersonDTO(person));
	}
	
	public ResponseEntity<List<PersonDTO>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(PersonDTO.convert(personRepository.findAll()));
	}
	
	public ResponseEntity<PersonDTO> findByHash(String hash){
		Optional<Person> optional = personRepository.findByHash(hash);
		if(optional.isPresent()) {
			return  ResponseEntity.status(HttpStatus.OK).body(new PersonDTO(optional.get()));
		}
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}