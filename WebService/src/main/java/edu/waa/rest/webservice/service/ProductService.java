package edu.waa.rest.webservice.service;

import java.util.List;
import java.util.Optional;

import edu.waa.rest.webservice.domain.Product;
import edu.waa.rest.webservice.domain.ProductType;
import edu.waa.rest.webservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductService   {
	
	@Autowired
	private ProductRepository productRepository;
		
	public Product save(Product product) {
		return productRepository.save(product);
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

	public Product getProduct(int productId){
		return  productRepository.getOne(productId);//.findOne(productId);
	}

	public List<Product> getAllProduct() {
		return  productRepository.findAll() ;
	}
	
	public List<Product> findByTextSearch(String criteria) {
		if (!criteria.contains("%")) {
			criteria = "%"+criteria+"%";
		}
		return productRepository.findByProductNameLikeOrDescriptionLikeAllIgnoreCase(criteria, criteria);
	}

	public List<Product> findByPrice(double minPrice, double maxPrice) {
		return  productRepository.findByPriceBetween(minPrice, maxPrice);
	}
	
	public List<Product> findByProductType(ProductType productType) {
		 return productRepository.findByProductType(productType);
	}

	public Product deleteProduct(int productId){
		Product product = productRepository.getOne(productId);
		productRepository.delete(product);
		return product;
	}

	public boolean updateProduct(Product oldProduct, Product newProduct) {
		boolean result = false;
		if(oldProduct != null){
			oldProduct.setDescription(newProduct.getDescription());
			oldProduct.setPrice(newProduct.getPrice());
			oldProduct.setProductName(newProduct.getProductName());
			oldProduct.setProductType(newProduct.getProductType());
			result = true;
		}
		return result;
	}
}
