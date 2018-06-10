package edu.waa.rest.webservice.controller;

import edu.waa.rest.webservice.domain.*;
import edu.waa.rest.webservice.service.PersonService;
import edu.waa.rest.webservice.service.ProductService;
import edu.waa.rest.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;

	@RequestMapping({"/", "/index", "/home"})
	public String homePage(Model model) {
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().toString().equals("anonymousUser")) {
			User user = userService.findUserByEmail(auth.getName());
			model.addAttribute("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
			model.addAttribute("role",user.getRoles().toString());
		}*/
		return "redirect:/listproducts";
	}

	@GetMapping({"/login"})
	public String loginView(){
		return "login";
	}

	@GetMapping({"/registration"})
	public String registration(Model model){
		model.addAttribute("person", new Person());
		return "registration";
	}

	/*@PostMapping({"/registration"})
	public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		User userExists = userService.findUserByEmail(user.getEmail());
		String view = "registration";
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (!bindingResult.hasErrors()) {
			userService.saveUser(user);
			model.addAttribute("successMessage", "User has been registered successfully");
			model.addAttribute("user", new User());
		}
		return view;
	}*/

}
