package com.samadai.shop.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samadai.shop.api.dtos.ProductDTO;
import com.samadai.shop.api.services.IProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private IProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> browse () {
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> show(@PathVariable Long id) {
		return ResponseEntity.ok(productService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> store(@RequestBody ProductDTO dto) {
		ProductDTO created = productService.save(dto);
		return ResponseEntity.created(URI.create("/api/products" + created.getId()))
				.body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
		return ResponseEntity.ok(productService.update(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
