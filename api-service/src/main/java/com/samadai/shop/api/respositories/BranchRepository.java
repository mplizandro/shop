package com.samadai.shop.api.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samadai.shop.api.entities.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
