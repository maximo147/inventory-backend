package com.company.inventory.controller;


import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponse;
import com.company.inventory.service.IProductService;
import com.company.inventory.utils.Utils;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin("**")
public class ProductController {

    @Autowired
    private IProductService service;

    /**
     * Get all products
     * @return
     * @throws Exception
     */
    @GetMapping
    private ResponseEntity<ProductResponse> getAll() throws Exception {
        return new ResponseEntity<>(service.search(), HttpStatus.OK);
    }

    /**
     * Get Product by ID
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(path = "{id}")
    private ResponseEntity<ProductResponse> getById(@PathVariable(name = "id") Integer id )  throws Exception {
        return new ResponseEntity<>(service.searchById(id), HttpStatus.OK);
    }

    /**
     * Save a Product
     * @param picture
     * @param nombre
     * @param price
     * @param quantity
     * @param category
     * @return
     * @throws Exception
     */
    @PostMapping
    private ResponseEntity<ProductResponse> post(
                                                @RequestParam("picture") MultipartFile picture,
                                                @RequestParam("nombre") String nombre,
                                                @RequestParam("price") Double price,
                                                @RequestParam("quantity") Integer quantity,
                                                @RequestParam("category") Integer category
    )  throws Exception {

        byte[] f1 = picture.getBytes();

        Product product = new Product();
        product.setNombre(nombre);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setPicture(Utils.compressZLib(f1));
        return new ResponseEntity<>(service.save(product, category), HttpStatus.CREATED);
    }

    @PutMapping(path = "edit/{id}")
    private ResponseEntity<ProductResponse> update(
                                                @RequestParam("picture") MultipartFile picture,
                                                @RequestParam("nombre") String nombre,
                                                @RequestParam("price") Double price,
                                                @RequestParam("quantity") Integer quantity,
                                                @RequestParam("category") Integer category,
                                                @PathVariable("id") Integer id
    ) throws Exception {
        byte[] aux = picture.getBytes();

        Product product = new Product();
        product.setId(id);
        product.setNombre(nombre);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setPicture(Utils.compressZLib(aux));

        return new ResponseEntity<>(service.update(product, id, category), HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{id}")
    private ResponseEntity<ProductResponse> delete(@PathVariable(name = "id") Integer id)  throws Exception {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @GetMapping(path = "name/{name}")
    private ResponseEntity<ProductResponse> getByName(@PathVariable("name") String name) throws Exception {
        return new ResponseEntity<>(service.getByNameLike(name), HttpStatus.OK);
    }

}
