package com.company.inventory.repository;

import com.company.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends JpaRepository<Product, Integer> {
}
