package com.samadai.shop.api.services;

import java.util.List;

import com.samadai.shop.api.dtos.SaleDTO;

public interface ISaleService {
	List<SaleDTO> findAll();
	SaleDTO findById(Long id);
	SaleDTO save(SaleDTO dto);
	SaleDTO update(Long id, SaleDTO dto);
	void delete(Long id);
}
