package com.company.inventory.controller;

import com.company.inventory.response.CategoryResponse;
import com.company.inventory.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<CategoryResponse> getAll() throws Exception {
        return new ResponseEntity<>(iCategoryService.search(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable(name = "id") Integer id) throws Exception {
        return new ResponseEntity<>(iCategoryService.searchById(id), HttpStatus.OK);
    }


}
