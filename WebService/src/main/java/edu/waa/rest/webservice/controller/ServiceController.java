package edu.waa.rest.webservice.controller;

import edu.waa.rest.webservice.domain.Order;
import edu.waa.rest.webservice.domain.Person;
import edu.waa.rest.webservice.domain.Product;
import edu.waa.rest.webservice.service.OrderService;
import edu.waa.rest.webservice.service.PersonService;
import edu.waa.rest.webservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {
    @Autowired
    PersonService personService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @PostMapping(value = "/rest/newproduct")
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping(value = "/rest/listproduct")
    public List<Product> allProduct(){
        return  productService.getAllProduct();
    }

    @GetMapping(value = "/rest/findproduct/{id}")
    public @ResponseBody Product findProductById(@PathVariable int id){
        return productService.getProduct(id);
    }
    @PutMapping(value = "/rest/updateproduct/{id}")
    public boolean updateProduct(@PathVariable int id, @RequestBody Product newProduct){
        Product oldProduct = productService.getProduct(id);
        return productService.updateProduct(oldProduct, newProduct);
    }

    @DeleteMapping(value = "/rest/deleteproduct/{id}")
    public @ResponseBody Product deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @PostMapping(value = "/rest/newperson")
    public @ResponseBody Person addPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @GetMapping(value = "/rest/listperson")
    public List<Person> allPerson(){
        return  personService.getAllPerson();
    }

    @PutMapping(value = "/rest/updateperson/{id}")
    public boolean updatePerson(@PathVariable long id, @RequestBody Person newPerson){
        Person oldPerson = personService.findById(id);
        return personService.updatePerson(oldPerson, newPerson);
    }

    @PostMapping(value = "/rest/neworder")
    public Order addOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping(value = "/rest/listorder")
    public List<Order> allOrder(){
        return  orderService.findAll();
    }
}
