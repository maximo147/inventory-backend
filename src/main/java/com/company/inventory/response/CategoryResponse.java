package com.company.inventory.response;

import com.company.inventory.model.Category;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class CategoryResponse extends ResponseGenerico{

    private List<Category> categories;

    public CategoryResponse(String type, String code, String date, List<Category> categories) {
        super(type, code, date);
        this.categories = categories;
    }
}
