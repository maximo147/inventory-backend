package com.company.inventory.service;

import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    CategoryResponse search() throws Exception;
    CategoryResponse searchById(Integer id) throws Exception;
    CategoryResponse save(Category category) throws Exception;
    CategoryResponse update(Category category, Integer id) throws Exception;
    void delete(Integer id) throws Exception;
}
