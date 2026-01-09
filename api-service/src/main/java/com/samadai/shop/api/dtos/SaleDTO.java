package com.samadai.shop.api.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
	private Long id;	
	private LocalDate saleDate;
	private String state;
	private Double total;
	
	private Long idBranch;
	
	private List<SaleDetailDTO> detail;
}
