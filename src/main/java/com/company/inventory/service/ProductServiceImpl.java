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
        List<Product> productList = iProductRepo.findAll();
        productList
                .forEach((p) -> {
                    if(p.getPicture().length > 0)
                        p.setPicture(Utils.decompressZLib(p.getPicture()));
                });
        return new ProductResponse("GET", "200", LocalDateTime.now().toString(), productList);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse searchById(Integer id) throws Exception {
        Product product = iProductRepo.findById(id).orElseThrow(() -> new Exception("No se encontró producto"));
        if(product != null){
            product.setPicture(Utils.decompressZLib(product.getPicture()));
        }
        return new ProductResponse("GET", "200", LocalDateTime.now().toString(), List.of(product));
    }

    @Override
    @Transactional
    public ProductResponse save(Product product, Integer category) throws Exception {
        Category category1 = iCategoryRepo.findById(category).orElseThrow(() -> new Exception("No se encontró la categoría"));
        product.setCategory(category1);
        iProductRepo.save(product);
        System.out.println(product.getPicture().length);
        return new ProductResponse("POST", "201", LocalDateTime.now().toString(), List.of(product));
    }

    @Override
    @Transactional
    public ProductResponse update(Product product, Integer id, Integer category) throws Exception {
        Category category1 = iCategoryRepo.findById(category).orElseThrow(() -> new Exception("No se encontro categoría"));
        Product p1 = iProductRepo.findById(id).orElseThrow(() -> new Exception("No se encontró producto"));
        product.setCategory(category1);
        if(product.getPicture().length <= 8 && p1.getPicture().length > 8){
            product.setPicture(p1.getPicture());
        }
        return new ProductResponse("PUT", "204", LocalDateTime.now().toString(), List.of(iProductRepo.save(product)));
    }

    @Override
    @Transactional
    public ProductResponse delete(Integer id) throws Exception {
        Product p1 = iProductRepo.findById(id).orElseThrow(() -> new Exception("No se encontró producto"));
        iProductRepo.delete(p1);
        return new ProductResponse("DELETE", "200", LocalDateTime.now().toString(), List.of());
    }
    @Override
    @Transactional(readOnly = true)
    public ProductResponse getByNameLike(String name) throws Exception{
        List<Product> products = iProductRepo.getByNameLike(name);
        products
                .forEach(p -> p.setPicture(Utils.decompressZLib(p.getPicture())));
        return new ProductResponse("GET", "200", LocalDateTime.now().toString(), products);
    }
}
