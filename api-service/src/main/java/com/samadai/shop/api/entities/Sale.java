package com.samadai.shop.api.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate saleDate;
	private String state;
	private Double total;
	
	@ManyToOne
	private Branch branch;
	
	@OneToMany (mappedBy = "sale", cascade = CascadeType.ALL
			, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<SaleDetail> detail = new ArrayList<>();
}
