package com.samadai.shop.api.services;

import java.util.List;

import com.samadai.shop.api.dtos.BranchDTO;

public interface IBranchService {
	List<BranchDTO> findAll();
	BranchDTO findById(Long id);
	BranchDTO save(BranchDTO dto);
	BranchDTO update(Long id, BranchDTO dto);
	void delete(Long id);
}
