package edu.waa.rest.webservice.repository;


import java.util.List;

import edu.waa.rest.webservice.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	public Person findByEmail(String email);
	
}
