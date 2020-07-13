package com.example.springboot.service;

import com.example.springboot.DAO.CategoryDAO;
import com.example.springboot.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> getAllCategory(){
        return this.categoryDAO.findAll();
    }
    public Category save (Category category) {
         return this.categoryDAO.save(category);
    }
    public void deleteById (Integer id){
        this.categoryDAO.deleteById(id);
    }
    public Optional<Category> findById (Integer id){
        return this.categoryDAO.findById(id);
    }
}