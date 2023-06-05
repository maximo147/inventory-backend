package com.company.inventory.service;


import com.company.inventory.model.Category;
import com.company.inventory.model.Product;
import com.company.inventory.repository.ICategoryRepo;
import com.company.inventory.repository.IProductRepo;
import com.company.inventory.response.ProductResponse;
import com.company.inventory.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductRepo iProductRepo;
    @Autowired
    private ICategoryRepo iCategoryRepo;

    @Override
    @Transactional(readOnly = true)
    public ProductResponse search() throws Exception {
        return new ProductResponse("GET", "200", LocalDateTime.now().toString(), iProductRepo.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse searchById(Integer id) throws Exception {
        Product product = iProductRepo.findById(id).orElseThrow(() -> new Exception("No se encontró producto"));
        product.setPicture(Utils.decompressZLib(product.getPicture()));
        return new ProductResponse("GET", "200", LocalDateTime.now().toString(), List.of(product));
    }

    @Override
    @Transactional(readOnly = false)
    public ProductResponse save(Product product, Integer category) throws Exception {
        Category category1 = iCategoryRepo.findById(category).orElseThrow(() -> new Exception("No se encontró la categoría"));
        product.setCategory(category1);
        iProductRepo.save(product);
        return new ProductResponse("POST", "201", LocalDateTime.now().toString(), List.of(product));
    }

    @Override
    @Transactional(readOnly = false)
    public ProductResponse update(Product product, Integer id) throws Exception {
        Product p1 = iProductRepo.findById(id).orElseThrow(() -> new Exception("No se encontró producto"));
        return new ProductResponse("PUT", "204", LocalDateTime.now().toString(), List.of(iProductRepo.save(product)));
    }

    @Override
    @Transactional(readOnly = false)
    public ProductResponse delete(Integer id) throws Exception {
        Product p1 = iProductRepo.findById(id).orElseThrow(() -> new Exception("No se encontró producto"));
        iProductRepo.delete(p1);
        return new ProductResponse("DELETE", "200", LocalDateTime.now().toString(), List.of());
    }
}
