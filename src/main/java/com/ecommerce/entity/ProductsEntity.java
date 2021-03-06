package com.ecommerce.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductsEntity implements Serializable {
	private static final long serialVersionUID = -8041040602080993567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String description;

	@Column(unique = true, nullable = false)
	private Integer code;

	@Column(nullable = false)
	private Integer stock;
}
