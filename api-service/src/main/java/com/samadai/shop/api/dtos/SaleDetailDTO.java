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
public class SaleDetailDTO {
	private Long id;
	private Long idProduct;
	private String nameProduct;
	private Double priceUnitProduct;
	private Double quantity;
	private Double subtotal;
}
