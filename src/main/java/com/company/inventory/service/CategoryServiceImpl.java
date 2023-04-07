package com.company.inventory.service;

import com.company.inventory.model.Category;
import com.company.inventory.repository.ICategoryRepo;
import com.company.inventory.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryRepo iCategoryRepo;

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse search() throws Exception {
        CategoryResponse cr = new CategoryResponse("GET", "200", LocalDateTime.now().toString(), iCategoryRepo.findAll());
        return cr;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse searchById(Integer id) throws Exception {
        Category category = iCategoryRepo.findById(id).orElseThrow(() -> new Exception("Categoría no encontrada"));
        CategoryResponse cr = new CategoryResponse("GET", "200", LocalDateTime.now().toString(), List.of(category));
        return cr;
    }

    @Override
    @Transactional()
    public CategoryResponse save(Category category) throws Exception {
        Category category1 = iCategoryRepo.save(category);
        CategoryResponse cr = new CategoryResponse("POST", "201", LocalDateTime.now().toString(), List.of(category1));
        return cr;
    }
}
