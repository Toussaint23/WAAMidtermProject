package edu.waa.rest.webservice.controller;

import edu.waa.rest.webservice.domain.Address;
import edu.waa.rest.webservice.domain.Person;
import edu.waa.rest.webservice.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"/", "/index", "/home"})
	public String homePage() {
		return "home";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
}
