package com.ecommerce.service;

import com.ecommerce.entity.ProductsEntity;
import com.ecommerce.exception.NotFound;
import com.ecommerce.exception.RegisterExists;
import com.ecommerce.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductsService {

	private final ProductsRepository repository;

	public ProductsService(ProductsRepository repository) {
		this.repository = repository;
	}

	public ProductsEntity findIdProducts(Long id) {
		log.info("Search for the product by id: " + id);
		Optional<ProductsEntity> productOpt = repository.findById(id);

		log.info("Checks if the product not exists");
		if (!productOpt.isPresent()) {
			throw new NotFound("Product with id :" + id + " not found!");
		}
		return productOpt.get();
	}

	public List<ProductsEntity> findAllProducts() {
		log.info("Retrieves the list of products in the bank!");
		return repository.findAll();
	}

	public List<ProductsEntity> findAllProductsByDescription(String description) {
		log.info("Retrieve a list of products by the specified description!");
		return repository.findByDescriptionIgnoreCase(description);
	}

	public ProductsEntity createProduct(ProductsEntity entity) {
		log.info("Checks if the code already exists in the registered bank!");
		if (repository.findByCode(entity.getCode()))
			throw new RegisterExists("Error: record already contained in the bank!");

		log.info("Saving a new product!");
		return repository.save(entity);
	}

	public void deleteProductById(Long id) {
		log.info("Retrieves the product using the findIdProducts function by passing id: " + id
				+ " and deleting the product in the bank with return");
		repository.deleteById(findIdProducts(id).getId());
	}
}
