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

    @PostMapping(value = "/newproduct")
    public Product addProduct(Product product) {
        return productService.save(product);
    }

    @GetMapping(value = "/listproduct")
    public List<Product> allProduct(){
        return  productService.getAllProduct();
    }

    @PutMapping(value = "/updateproduct/{id}")
    public boolean updateProduct(@PathVariable int id, Product newProduct){
        Product oldProduct = productService.getProduct(id);
        return productService.updateProduct(oldProduct, newProduct);
    }

    @DeleteMapping(value = "/deleteproduct/{id}")
    public Product deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @PostMapping(value = "/newperson")
    public Person addPerson(Person person) {
        return personService.savePerson(person);
    }

    @GetMapping(value = "/listperson")
    public List<Person> allPerson(){
        return  personService.getAllPerson();
    }

    @PutMapping(value = "/updateperson/{id}")
    public boolean updatePerson(@PathVariable long id, Person newPerson){
        Person oldPerson = personService.findById(id);
        return personService.updatePerson(oldPerson, newPerson);
    }

    @PostMapping(value = "/neworder")
    public Order addOrder(Order order) {
        return orderService.save(order);
    }

    @GetMapping(value = "/listorder")
    public List<Order> allOrder(){
        return  orderService.findAll();
    }
}
