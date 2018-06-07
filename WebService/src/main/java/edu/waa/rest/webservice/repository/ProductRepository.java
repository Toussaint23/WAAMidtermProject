package edu.waa.rest.webservice.repository;

import edu.waa.rest.webservice.domain.Product;
import edu.waa.rest.webservice.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Serializable>{

	public List<Product> findByProductNameLikeOrDescriptionLikeAllIgnoreCase(String productName, String description); 
	public List<Product> findByProductType(ProductType productType);
	public List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}
