package edu.waa.rest.webservice.service;

import java.util.List;

import edu.waa.rest.webservice.domain.Person;
import edu.waa.rest.webservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	public List<Person> findByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	public Person findById(Long id) {
		return personRepository.getOne(id);//.findOne(id);
	}

	public void removePerson(Person person) {
		personRepository.delete(person);
	}

}
