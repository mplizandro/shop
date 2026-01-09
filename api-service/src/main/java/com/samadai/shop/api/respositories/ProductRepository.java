package com.samadai.shop.api.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samadai.shop.api.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
