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

import com.samadai.shop.api.dtos.BranchDTO;
import com.samadai.shop.api.services.IBranchService;

@RestController
@RequestMapping("/api/branchs")
public class BranchController {
	@Autowired
	private IBranchService branchService;
	
	@GetMapping
	public ResponseEntity<List<BranchDTO>> browse () {
		return ResponseEntity.ok(branchService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BranchDTO> show(@PathVariable Long id) {
		return ResponseEntity.ok(branchService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<BranchDTO> store(@RequestBody BranchDTO dto) {
		BranchDTO created = branchService.save(dto);
		return ResponseEntity.created(URI.create("/api/products" + created.getId()))
				.body(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BranchDTO> update(@PathVariable Long id, @RequestBody BranchDTO dto) {
		return ResponseEntity.ok(branchService.update(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		branchService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
