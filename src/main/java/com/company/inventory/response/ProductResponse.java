package com.company.inventory.response;

import com.company.inventory.model.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductResponse extends ResponseGenerico{

    private List<Product> data;

    public ProductResponse(String type, String code, String date, List<Product> products) {
        super(type, code, date);
        this.data = products;
    }
}
