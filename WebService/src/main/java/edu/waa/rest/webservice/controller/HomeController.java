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
public class HomeController {
	@Autowired
	ProductService productService;

	@GetMapping({"/", "/index", "/home"})
	public String homePage() {;
		return "redirect:/listproducts";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
}
