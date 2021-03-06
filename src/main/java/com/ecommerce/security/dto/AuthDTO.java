package com.ecommerce.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Class representing the user's credentials to authenticate to the system")
public class AuthDTO implements Serializable {
	private static final long serialVersionUID = -6605652716353887238L;

	@NotEmpty(message = "Username cannot be empty")
	@NotNull(message = "Username cannot be null")
	@ApiModelProperty(notes = "User username", name = "username", required = true, example = "admin")
	private String userName;

	@NotEmpty(message = "Password cannot be empty")
	@NotNull(message = "Password cannot be null")
	@ApiModelProperty(notes = "User password", name = "password", required = true, example = "123@45")
	private String password;
}
