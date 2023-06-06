package com.company.inventory.service;

import com.company.inventory.model.Category;
import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponse;
import com.company.inventory.response.ProductResponse;

import java.util.List;

public interface IProductService {
    ProductResponse search() throws Exception;
    ProductResponse searchById(Integer id) throws Exception;
    ProductResponse save(Product product, Integer id) throws Exception;
    ProductResponse update(Product product, Integer id, Integer category) throws Exception;
    ProductResponse delete(Integer id) throws Exception;
    ProductResponse getByNameLike(String name) throws Exception;
}
