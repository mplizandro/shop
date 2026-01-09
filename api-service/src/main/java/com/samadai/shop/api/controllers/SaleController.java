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

import com.samadai.shop.api.dtos.SaleDTO;
import com.samadai.shop.api.services.ISaleService;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
	@Autowired
	private ISaleService saleService;
	
	@GetMapping
	public ResponseEntity<List<SaleDTO>> browse () {
		return ResponseEntity.ok(saleService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SaleDTO> show(@PathVariable Long id) {
		return ResponseEntity.ok(saleService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<SaleDTO> store(@RequestBody SaleDTO dto) {
		SaleDTO created = saleService.save(dto);
		return ResponseEntity.created(URI.create("/api/sales" + created.getId()))
				.body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SaleDTO> update(@PathVariable Long id, @RequestBody SaleDTO dto) {
		return ResponseEntity.ok(saleService.update(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		saleService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
