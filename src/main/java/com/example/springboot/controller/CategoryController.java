package com.example.springboot.controller;

import com.example.springboot.entity.Category;
import com.example.springboot.entity.Question;
import com.example.springboot.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class CategoryController {
    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @CrossOrigin
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategory(){
        log.debug("---------------REST request to getAllCategory--------------------");
        return new ResponseEntity<List<Category>>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public Category createCategory(@RequestBody Category category){
        log.debug("---------------REST request to createCategory--------------------");
        return this.categoryService.save(category);
    }

    @RequestMapping(value = "/deleteCategoryById/{id}", method = RequestMethod.DELETE)
    public void deleteCategoryById (@PathVariable(value = "id") Integer id) {
        log.debug("---------------REST request to deleteCategoryById--------------------");
        this.categoryService.deleteById(id);
    }
    @RequestMapping(value = "/category/{id}",method = RequestMethod.PUT)
    public Category updateCategory (@RequestBody Category category, @PathVariable(value ="id") Integer id) {
        log.debug("---------------REST request to deleteCategoryById--------------------");
        Optional<Category> categoryOptional = categoryService.findById(id);
        category.setId(categoryOptional.get().getId());
        return categoryService.save(category);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> categoryDetail(@PathVariable Integer id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        return categoryOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
