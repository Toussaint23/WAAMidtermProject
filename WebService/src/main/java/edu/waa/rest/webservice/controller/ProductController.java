package edu.waa.rest.webservice.controller;

import edu.waa.rest.webservice.domain.Product;
import edu.waa.rest.webservice.domain.ProductType;
import edu.waa.rest.webservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping({"/listproducts"})
	public String homePage(Model model) {
		List<Product> products = productService.getAllProduct();
		model.addAttribute("products",products);
		return "list_products";
	}

	@GetMapping({"/addproduct"})
	public String addProductView(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("productType", ProductType.values());
		return "add_product";
	}

	@PostMapping({"/addproduct"})
	public String addProductView(Product product, Model model) {
		Product result = productService.save(product);
		String view = (result != null ) ? "redirect:/home" : "/addproduct";
		return view;
	}

	@GetMapping({"/editlocalproduct/{id}"})
	public String editProductView(@PathVariable int id, Model model) {
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		model.addAttribute("productType", ProductType.values());
		return "edit_product";
	}

	@PostMapping({"/editlocalproduct/{id}"})
	public String editProduct(@PathVariable int id,  Product product) {
		Product result = productService.getProduct(id);
		String view = (productService.updateProduct(result, product)) ? "redirect:/home" : "/editlocalproduct/"+id;
		return view;
	}
	@GetMapping({"/deletelocalproduct/{id}"})
	public String deleteProductView(@PathVariable int id, Model model) {
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		model.addAttribute("productType", ProductType.values());
		return "delete_product";
	}

	@PostMapping({"/deletelocalproduct/{id}"})
	public String deleteProduct(@PathVariable int id) {
		String view = (productService.deleteProduct(id) != null) ? "redirect:/home" : "/deletelocalproduct/"+id;
		return view;
	}
}
