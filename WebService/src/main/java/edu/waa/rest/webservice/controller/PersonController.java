package edu.waa.rest.webservice.controller;

import edu.waa.rest.webservice.domain.Address;
import edu.waa.rest.webservice.domain.Person;
import edu.waa.rest.webservice.domain.Product;
import edu.waa.rest.webservice.domain.ProductType;
import edu.waa.rest.webservice.service.PersonService;
import edu.waa.rest.webservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PersonController {
	@Autowired
	PersonService personService;

	@GetMapping({"/addperson"})
	public String addPersonView(Model model) {
		model.addAttribute("person", new Person());
		return "add_person";
	}

	@PostMapping({"/addperson"})
	public String addPerson(Person person) {
		Person result = personService.savePerson(person);
		String view = (result != null ) ? "redirect:/home" : "/addperson";
		return view;
	}

	@GetMapping({"/listpersons"})
	public String people(Model model) {
		List<Person> personList = personService.getAllPerson();
		model.addAttribute("people",personList);
		return "list_persons";
	}

	@GetMapping({"/editlocalperson/{id}"})
	public String editPersonView(@PathVariable long id, Model model) {
		Person person = personService.findById(id);
		model.addAttribute("person", person);
		return "edit_person";
	}

	@PostMapping({"/editlocalperson/{id}"})
	public String editPerson(@PathVariable long id,  Person person) {
		Person result = personService.findById(id);
		String view = (personService.updatePerson(result, person)) ? "redirect:/listpersons" : "/editlocalperson/"+id;
		return view;
	}

	@GetMapping({"/deletelocalperson/{id}"})
	public String deletePersonView(@PathVariable long id, Model model) {
		Person person= personService.findById(id);
		model.addAttribute("person", person);
		return "delete_person";
	}

	@PostMapping({"/deletelocalperson/{id}"})
	public String deletePerson(@PathVariable long id) {
		personService.removePerson(personService.findById(id));
		return"redirect:/listpersons";
	}
}
