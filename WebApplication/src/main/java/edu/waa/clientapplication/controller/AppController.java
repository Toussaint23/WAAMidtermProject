package edu.waa.clientapplication.controller;

import edu.waa.clientapplication.model.Product;
import edu.waa.clientapplication.model.ProductType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class AppController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping({"/", "/index","/home", "/Welcome"})
    public String appRoot(){
        return "redirect:/welcome";
    }

    @GetMapping(value = "/welcome")
    public String welcomePage(Model model){
        final String url = "http://localhost:8080/rest/listproduct";
        List<Product> products = restTemplate.getForObject(url, List.class);
        model.addAttribute("products", products);
        return "list_products";
    }

    @GetMapping(value = "/addproduct")
    public String addProductView(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("productType", ProductType.values());
        return "add_product";
    }

    @PostMapping(value = "/addproduct")
    public String addProduct(Product newProduct, Model model){
        String view = "/addproduct";
        final String url = "http://localhost:8080/rest/newproduct";
        Product product = restTemplate.postForObject(url, newProduct, Product.class);
        if(product != null){
            model.addAttribute("product", product);
            view = "redirect:/";
        }
        return view;
    }

    @GetMapping(value = "/editproduct/{id}")
    public String editProductView(@PathVariable int id,  Model model){
        final String url = "http://localhost:8080/rest/findproduct/"+id;
        Product product = restTemplate.getForObject(url, Product.class);
        model.addAttribute("product", product);
        model.addAttribute("productType", ProductType.values());
        return "edit_product";
    }

    @PostMapping(value = "/editproduct/{id}")
    public String editProduct(@PathVariable int id, Product newProduct, Model model){
        final String url = "http://localhost:8080/rest/updateproduct/"+id;
        restTemplate.put(url, newProduct);
        return "redirect:/";
    }

    @GetMapping(value = "/deleteproduct/{id}")
    public String deleteProductView(@PathVariable int id,  Model model){
        final String url = "http://localhost:8080/rest/findproduct/"+id;
        Product product = restTemplate.getForObject(url, Product.class);
        model.addAttribute("product", product);
        model.addAttribute("productType", ProductType.values());
        return "delete_product";
    }

    @PostMapping(value = "/deleteproduct/{id}")
    public String deleteProduct(@PathVariable int id){
        final String url = "http://localhost:8080/rest/deleteproduct/"+id;
        restTemplate.delete(url);
        return "redirect:/";
    }
}
