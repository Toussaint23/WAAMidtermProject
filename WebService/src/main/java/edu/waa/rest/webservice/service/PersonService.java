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

	public List<Person> getAllPerson(){return personRepository.findAll();}

	public boolean updatePerson(Person old, Person person){
		boolean result = false;
		if(old != null){
			old.setPhone(person.getPhone());
			old.setLastName(person.getLastName());
			old.setFirstName(person.getFirstName());
			old.setEnable(person.isEnable());
			old.setEmail(person.getEmail());
			old.setAddress(person.getAddress());
			result = true;
		}
		return result;
	}

}
