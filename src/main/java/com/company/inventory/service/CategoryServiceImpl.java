package com.company.inventory.service;

import com.company.inventory.model.Category;
import com.company.inventory.repository.ICategoryRepo;
import com.company.inventory.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
