package edu.waa.rest.webservice.repository;

import java.util.Date;
import java.util.List;

import edu.waa.rest.webservice.domain.Order;
import edu.waa.rest.webservice.domain.Person;
import edu.waa.rest.webservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	List<Order> findDistinctOrderByOrderLines_Product(Product product);
	List<Order> findOrderByPerson(Person person);
	List<Order> findOrderByOrderDateBetween(Date minDate, Date maxDate);
	

}
