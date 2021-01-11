package com.heftyb.supersupper.services;

import com.heftyb.supersupper.models.Category;

import java.util.List;

public interface CategoryService
{
    List<Category> findAll();

    Category findCategoryById(long id);

    Category save(Category cat);

    void delete(long id);
}
