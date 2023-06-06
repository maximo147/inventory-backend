package com.company.inventory.repository;

import com.company.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRepo extends JpaRepository<Product, Integer> {
    //@Query(value = "SELECT p FROM Product p WHERE p.nombre like %?1%")
    @Query(value = "select * from producto p WHERE p.nombre like %:name%", nativeQuery = true)
    List<Product> getByNameLike(String name) throws Exception;
}
