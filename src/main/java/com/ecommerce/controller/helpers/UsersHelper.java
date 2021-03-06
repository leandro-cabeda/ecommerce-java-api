package com.ecommerce.controller.helpers;


import com.ecommerce.dto.UsersDTO;
import com.ecommerce.entity.UsersEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsersHelper {

	private final ModelMapper modelMapper;

	public UsersHelper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public UsersDTO toModel(UsersEntity usersEntity) {
		return modelMapper.map(usersEntity, UsersDTO.class);
	}

	public UsersEntity toEntity(UsersDTO usersDTO) {
		return modelMapper.map(usersDTO, UsersEntity.class);
	}
}
