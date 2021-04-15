package com.proiect.lamunca.service;

import com.proiect.lamunca.entity.db1.Category;
import com.proiect.lamunca.repository.db1.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements IService<Category, String>{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public boolean add(Category element) {
        return false;
    }

    @Override
    public List<Category> getAll() {
        try {
            return categoryRepository.findAll();
        }
        catch (Exception ex){
            return null;
        }
    }

    public Category getByName(String name){
        try {
            return categoryRepository.getByName(name);
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public boolean getById(String s) {
        return false;
    }
}
