package com.samadai.shop.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samadai.shop.api.dtos.ProductDTO;
import com.samadai.shop.api.entities.Product;
import com.samadai.shop.api.exceptions.NotFoundException;
import com.samadai.shop.api.mappers.Mapper;
import com.samadai.shop.api.respositories.ProductRepository;
import com.samadai.shop.api.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private ProductRepository repository;

	@Override
	public List<ProductDTO> findAll() {
		return repository.findAll().stream()
			.map(Mapper::toDTO)
			.toList();
	}

	@Override
	public ProductDTO findById(Long id) {
		Product p = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("No existe el producto"));
		
		return Mapper.toDTO(p);
	}

	@Override
	public ProductDTO save(ProductDTO dto) {
		Product p = Product.builder()
				.name(dto.getName())
				.category(dto.getCategory())
				.priceUnit(dto.getPriceUnit())
				.unit(dto.getUnit())
				.stock(dto.getStock())
				.build();
		
		return Mapper.toDTO(repository.save(p));
	}

	@Override
	public ProductDTO update(Long id, ProductDTO dto) {
		Product p = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("No existe el producto"));
		
		p.setName(dto.getName());
		p.setCategory(dto.getCategory());
		p.setPriceUnit(dto.getPriceUnit());
		p.setUnit(dto.getUnit());
		p.setStock(dto.getStock());
		
		return Mapper.toDTO(repository.save(p));
	}

	@Override
	public void delete(Long id) {
		if ( !repository.existsById(id) )
			throw new NotFoundException("No existe el produto para eliminar");
		
		repository.deleteById(id);
	}

}
