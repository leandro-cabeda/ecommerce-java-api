package com.ecommerce.dto;

import io.swagger.annotations.*;
import lombok.*;
import javax.validation.constraints.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Class that represents the model of the products")
public class ProductsDTO implements Serializable {
	private static final long serialVersionUID = 7464128111403246540L;

	@ApiModelProperty(notes = "Product Id", name = "id", required = false, example = "1", position = 0)
	private Long id;

	@NotNull(message = "Description cannot be null")
	@NotEmpty(message = "Description cannot be empty")
	@ApiModelProperty(notes = "Description title", name = "description", required = true, example = "Notebook Asus", position = 1)
	private String description;

	@NotNull(message = "Code cannot be null")
	@ApiModelProperty(notes = "Code of product", name = "code", required = true, example = "145", position = 2)
	private Integer code;

	@NotNull(message = "Stock cannot be null")
	@ApiModelProperty(notes = "Stock of product", name = "stock", required = true, example = "25", position = 3)
	private Integer stock;

}
