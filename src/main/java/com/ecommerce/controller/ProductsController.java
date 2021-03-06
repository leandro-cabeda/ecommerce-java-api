package com.ecommerce.controller;

import com.ecommerce.controller.helpers.ProductsHelper;
import com.ecommerce.dto.ProductsDTO;
import com.ecommerce.service.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@Api(tags = {"Products EndPoint"})
public class ProductsController {

	private final ProductsService service;
	private final ProductsHelper helper;

	public ProductsController(ProductsService service, ProductsHelper helper) {
		this.service = service;
		this.helper = helper;
	}

	@GetMapping(value = "/id/{id}", produces = "application/json")
	@ApiOperation(value = "Product search by id")
	public ResponseEntity<ProductsDTO> findToolById(
			@ApiParam(value = "Id to search for a particular product", required = true, example = "1")
			@PathVariable("id") Long id) {

		log.info("Returning the product and converting them to productDTO!");
		return ResponseEntity.ok(helper.toModel(service.findIdProducts(id)));
	}

	@GetMapping(produces = "application/json")
	@ApiOperation(value = "Lists all products")
	public ResponseEntity<List<ProductsDTO>> findAllProducts() {

		log.info("Returning the list of entity products and converting them to productsDTO!");
		return ResponseEntity.ok(helper.toModel(service.findAllProducts()));
	}

	@GetMapping(value = "/{description}", produces = "application/json")
	@ApiOperation(value = "Lists all products by the specified description")
	public ResponseEntity<List<ProductsDTO>> findAllProductsByDescription(
			@ApiParam(value = "Description to search which products they have", required = true, example = "notebook dell")
			@PathVariable("description") String description) {

		log.info("Returning the list of products from the specified entity by the inserted description and converting them to productsDTO!");
		return ResponseEntity.ok(helper.toModel(service.findAllProductsByDescription(description)));
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	@ApiOperation(value = "Create a new product")
	public ResponseEntity<ProductsDTO> createProduct(@Valid @RequestBody ProductsDTO productsDTO, BindingResult result) {

		log.info("Checks if the information is correct by the validation implemented!");
		if (result.hasErrors())
			return ResponseEntity.badRequest().body(productsDTO);

		log.info("Converts data to entity when sending through the service and returns converting to DTO");
		ProductsDTO product = helper.toModel(service.createProduct(helper.toEntity(productsDTO)));

		log.info("Adds the url's URI with Id!");
		URI uri = getUri(product.getId());

		return ResponseEntity.created(uri).body(product);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	@ApiOperation(value = "Delete a product")
	public ResponseEntity<Void> deleteProductId(
			@ApiParam(value = "Id to delete a specific product", required = true, example = "1")
			@PathVariable("id") Long id) {

		log.info("Receive the product id in the parameter and delete!");
		service.deleteProductById(id);

		return ResponseEntity.noContent().build();
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}
