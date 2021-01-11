package com.heftyb.supersupper.repository;

import com.heftyb.supersupper.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>
{
    List<Ingredient> findByNameContainingIgnoreCase(String name);
}
