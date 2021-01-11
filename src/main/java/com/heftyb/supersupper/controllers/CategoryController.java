package com.heftyb.supersupper.controllers;

import com.heftyb.supersupper.models.Category;
import com.heftyb.supersupper.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/categories",
        produces = "application/json")
    public ResponseEntity<?> findAllCategories()
    {
        List<Category> list = categoryService.findAll();
        return new ResponseEntity<>(list,
            HttpStatus.OK);
    }

    @GetMapping(value = "/category/{id}",
    produces = "application/json")
    public ResponseEntity<?> findCategoryById(@PathVariable long id)
    {
        Category c = categoryService.findCategoryById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PostMapping(value = "/category",
    consumes = "application/json")
    public ResponseEntity<?> addCategory(@Valid @RequestBody Category newCategory)
    {
        newCategory.setCategoryid(0);
        newCategory = categoryService.save(newCategory);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRecipeURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{categoryid}")
            .buildAndExpand(newCategory.getCategoryid())
            .toUri();
        responseHeaders.setLocation(newRecipeURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable long id)
    {
        categoryService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
