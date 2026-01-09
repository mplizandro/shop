package com.samadai.shop.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SaleDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double quantity;
	private Double priceUnit;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "saleId")
	private Sale sale;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "productId")
	private Product product;
}
