package com.heftyb.supersupper.services;

import com.heftyb.supersupper.exceptions.ResourceNotFoundException;
import com.heftyb.supersupper.models.Ingredient;
import com.heftyb.supersupper.models.RecipeIngredient;
import com.heftyb.supersupper.repository.CategoryRepository;
import com.heftyb.supersupper.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "ingredientService")
public class IngredientServiceImp implements IngredientService
{

    @Autowired
    private IngredientRepository ingrepo;

    @Autowired
    private CategoryRepository catrepo;

    @Override
    public List<Ingredient> findAll()
    {
        List<Ingredient> ingredients = new ArrayList<>();
        ingrepo.findAll()
            .iterator()
            .forEachRemaining(ingredients::add);

        return ingredients;
    }

    @Override
    public List<Ingredient> findByNameContaining(String name)
    {
        return ingrepo.findByNameContainingIgnoreCase(name.toLowerCase());
    }

    @Override
    public Ingredient findIngredientById(long id)
    {
        return ingrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ingredient id " + id + " not found!"));
    }

    @Override
    public Ingredient save(Ingredient ingredient)
    {
        Ingredient newIngredient = new Ingredient();

        if (ingredient.getIngredientid() != 0)
        {
            ingrepo.findById(ingredient.getIngredientid())
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient id " + ingredient.getIngredientid() + " not found!"));
            newIngredient.setIngredientid(ingredient.getIngredientid());
        }

        newIngredient.setName(ingredient.getName());
        newIngredient.setCategory(ingredient.getCategory());
        newIngredient.setDescription(ingredient.getDescription());

        newIngredient.getRecipes()
            .clear();

        for (RecipeIngredient r : ingredient.getRecipes())
        {
            newIngredient.getRecipes()
                .add(new RecipeIngredient(r.getRecipe(),
                    newIngredient,
                    r.getQty()));
        }

        return ingrepo.save(newIngredient);
    }

    @Override
    public Ingredient update(
        Ingredient ingredient,
        long id)
    {
        Ingredient currentIngredient = findIngredientById(id);
        if (ingredient.getName() != null)
        {
            currentIngredient.setName(ingredient.getName());
        }

        if (ingredient.getCategory() != null)
        {
            currentIngredient.setCategory(ingredient.getCategory());
        }

        if (ingredient.getDescription() != null)
        {
            currentIngredient.setDescription(ingredient.getDescription());
        }

        if (ingredient.getRecipes()
            .size() > 0)
        {
            currentIngredient.getRecipes()
                .clear();

            for (RecipeIngredient r : ingredient.getRecipes())
            {
                currentIngredient.getRecipes()
                    .add(new RecipeIngredient(r.getRecipe(),
                        currentIngredient,
                        r.getQty()));
            }

        }

        return ingrepo.save(currentIngredient);
    }

    @Override
    public void delete(long id)
    {
        ingrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ingredient id " + id + " not found!"));
        ingrepo.deleteById(id);
    }
}
