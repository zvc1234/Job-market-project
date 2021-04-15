package com.proiect.lamunca.controller;

import com.proiect.lamunca.entity.db1.Category;
import com.proiect.lamunca.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/Category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET, produces = "application/json")
    public List<Category> getAllcategories(){
        return categoryService.getAll();
    }
}
