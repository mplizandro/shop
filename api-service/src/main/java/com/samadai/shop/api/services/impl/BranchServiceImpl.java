package com.samadai.shop.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samadai.shop.api.dtos.BranchDTO;
import com.samadai.shop.api.entities.Branch;
import com.samadai.shop.api.exceptions.NotFoundException;
import com.samadai.shop.api.mappers.Mapper;
import com.samadai.shop.api.respositories.BranchRepository;
import com.samadai.shop.api.services.IBranchService;

@Service
public class BranchServiceImpl implements IBranchService {

	@Autowired
	private BranchRepository repository;
	
	@Override
	public List<BranchDTO> findAll() {
		return repository.findAll().stream()
				.map(Mapper::toDTO)
				.toList();
	}

	@Override
	public BranchDTO findById(Long id) {
		Branch b = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("No existe la sucursal"));
		
		return Mapper.toDTO(b);
	}

	@Override
	public BranchDTO save(BranchDTO dto) {
		Branch branch = Branch.builder()
				.name(dto.getName())
				.address(dto.getAddress())
				.build();
		
		return Mapper.toDTO(repository.save(branch));
	}

	@Override
	public BranchDTO update(Long id, BranchDTO dto) {
		Branch b = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("No existe la sucursal"));
		
		b.setName(dto.getName());
		b.setAddress(dto.getAddress());
		
		return Mapper.toDTO(repository.save(b));
	}

	@Override
	public void delete(Long id) {
		if ( !repository.existsById(id) )
			throw new NotFoundException("No existe la sucursal para eliminar");
		
		repository.deleteById(id);
	}

}
