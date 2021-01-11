package com.heftyb.supersupper.services;

import com.heftyb.supersupper.exceptions.ResourceNotFoundException;
import com.heftyb.supersupper.models.Category;
import com.heftyb.supersupper.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "categoryService")
public class CategoryServiceImp implements CategoryService
{
    @Autowired
    private CategoryRepository catrepo;

    @Override
    public List<Category> findAll()
    {
        List<Category> list = new ArrayList<>();
        catrepo.findAll().iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Category findCategoryById(long id)
    {
        return catrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Catefory id " + id + " not found!"));
    }

    @Override
    public Category save(Category cat)
    {
        Category category = new Category();
        category.setName(cat.getName());

//        for (Ingredient i : cat.getIngredients())
//        {
//            category.getIngredients().add(i);
//        }
        return catrepo.save(category);
    }

    @Override
    public void delete(long id)
    {
        catrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category id " + id + " not found!"));
        catrepo.deleteById(id);
    }
}
