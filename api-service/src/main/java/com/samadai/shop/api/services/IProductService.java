package com.samadai.shop.api.services;

import java.util.List;

import com.samadai.shop.api.dtos.ProductDTO;


public interface IProductService {
	List<ProductDTO> findAll();
	ProductDTO findById(Long id);
	ProductDTO save(ProductDTO dto);
	ProductDTO update(Long id, ProductDTO dto);
	void delete(Long id);
}
