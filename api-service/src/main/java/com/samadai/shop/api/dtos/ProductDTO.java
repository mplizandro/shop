package com.samadai.shop.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
	private Long id;
	private String name;
	private String category;
	private Double priceUnit;
	private String unit;
	private int stock;
}
