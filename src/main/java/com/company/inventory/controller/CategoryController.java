package com.company.inventory.controller;

import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponse;
import com.company.inventory.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody Category category) throws Exception {
        return new ResponseEntity<>(iCategoryService.save(category), HttpStatus.CREATED);
    }
    @PutMapping("edit/{id}")
    public ResponseEntity<CategoryResponse> save(@RequestBody Category category, @PathVariable(name = "id") Integer id) throws Exception {
        return new ResponseEntity<>(iCategoryService.update(category, id), HttpStatus.OK);
    }


}
