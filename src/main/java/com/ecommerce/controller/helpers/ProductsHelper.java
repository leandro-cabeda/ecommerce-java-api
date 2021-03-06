package com.ecommerce.controller.helpers;

import com.ecommerce.dto.ProductsDTO;
import com.ecommerce.entity.ProductsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Component
public class ProductsHelper {

	private final ModelMapper modelMapper;

	public ProductsHelper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public ProductsDTO toModel(ProductsEntity productsEntity) {
		return modelMapper.map(productsEntity, ProductsDTO.class);
	}
	
	public ProductsEntity toEntity(ProductsDTO productsDTO) {
		return modelMapper.map(productsDTO, ProductsEntity.class);
	}
	
	public List<ProductsDTO> toModel(List<ProductsEntity> productsEntities){
		return productsEntities.stream().map(this::toModel).collect(toList());
	}
}
