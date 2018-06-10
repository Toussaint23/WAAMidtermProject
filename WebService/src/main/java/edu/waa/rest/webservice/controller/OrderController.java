package edu.waa.rest.webservice.controller;

import edu.waa.rest.webservice.domain.Order;
import edu.waa.rest.webservice.domain.Orderline;
import edu.waa.rest.webservice.domain.Person;
import edu.waa.rest.webservice.domain.Product;
import edu.waa.rest.webservice.service.OrderService;
import edu.waa.rest.webservice.service.PersonService;
import edu.waa.rest.webservice.service.ProductService;
import edu.waa.rest.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	PersonService personService;

	@GetMapping({"/addorder"})
	public String addPersonView(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("orderline", new Orderline());
		return "add_order";
	}

	@PostMapping({"/addorder"})
	public String createNewUser(@RequestParam int productId, @RequestParam int quantity) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Person person = personService.findByEmail(auth.getName().trim());
		Product product = productService.getProduct(productId);
		Orderline orderline= new Orderline();
		Order order = new Order();
		orderline.setProduct(product);
		orderline.setQuantity(quantity);
		order.setPerson(person);
		order.setOrderDate(new java.util.Date());
		orderline.setOrder(order);
		order.addOrderLine(orderline);
		orderService.save(order);
		return "list_products";
	}



	@GetMapping({"/listorders"})
	public String people(Model model) {
		List<Order> orderList = orderService.findAll();
		model.addAttribute("orders",orderList);
		return "list_orders";
	}

	/*@GetMapping({"/editlocalperson"})
	public String myInfoView(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Person person = personService.findByEmail(auth.getName().trim());
		model.addAttribute("person", person);
		return "personal";
	}

	@GetMapping({"/editlocalperson/{id}"})
	public String editPersonView(@PathVariable long id, Model model) {
		Person person = personService.findById(id);
		model.addAttribute("person", person);
		return "personal";
	}

	@PostMapping({"/editlocalperson"})
	public String myInfo(Person person) {
		Person result = personService.findByEmail(person.getEmail());
		String view = (personService.updatePerson(result, person)) ? "redirect:/" : "/editlocalperson";
		return view;
	}


	@GetMapping({"/deletelocalperson/{id}"})
	public String deletePersonView(@PathVariable long id, Model model) {
		Person person= personService.findById(id);
		model.addAttribute("person", person);
		return "delete_personal";
	}

	@PostMapping({"/deletelocalperson/{id}"})
	public String deletePerson(@PathVariable long id) {
		personService.removePerson(personService.findById(id));
		return"redirect:/listpersons";
	}*/
}
