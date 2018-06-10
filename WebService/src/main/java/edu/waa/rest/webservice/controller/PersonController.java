package edu.waa.rest.webservice.controller;

import edu.waa.rest.webservice.domain.*;
import edu.waa.rest.webservice.service.PersonService;
import edu.waa.rest.webservice.service.ProductService;
import edu.waa.rest.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PersonController {
	@Autowired
	PersonService personService;

	@Autowired
	UserService userService;

	@GetMapping({"/addperson"})
	public String addPersonView(Model model) {
		model.addAttribute("person", new Person());
		return "add_person";
	}

	@PostMapping({"/registration"})
	public String createNewUser(@Valid Person person, BindingResult bindingResult, Model model) {
		User userExists = userService.findUserByEmail(person.getEmail());
		String view = "registration";
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (!bindingResult.hasErrors()) {
			userService.setUser(person.getAccount(), "ADMIN", person.getEmail());
			personService.savePerson(person);
			model.addAttribute("successMessage", "User has been registered successfully");
			model.addAttribute("person", new Person());
		}
		return view;
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
