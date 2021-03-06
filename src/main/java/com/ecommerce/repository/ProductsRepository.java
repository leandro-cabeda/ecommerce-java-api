package com.ecommerce.repository;

import com.ecommerce.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {

	List<ProductsEntity> findAll();
	boolean findByCode(Integer code);
	List<ProductsEntity> findByDescriptionIgnoreCase(String description);
}
