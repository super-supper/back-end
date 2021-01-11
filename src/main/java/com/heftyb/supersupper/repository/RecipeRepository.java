package com.heftyb.supersupper.repository;

import com.heftyb.supersupper.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long>
{
    List<Recipe> findByRecipenameContainingIgnoreCase(String name);
}
