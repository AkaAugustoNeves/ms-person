package br.com.pilares.msperson.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pilares.msperson.model.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	Optional<Person> findByHash(String hash);
	
}
