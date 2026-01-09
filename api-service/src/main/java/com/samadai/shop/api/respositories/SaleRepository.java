package com.samadai.shop.api.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samadai.shop.api.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

}
