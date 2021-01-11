package com.heftyb.supersupper.services;

import com.heftyb.supersupper.exceptions.ResourceNotFoundException;
import com.heftyb.supersupper.models.*;
import com.heftyb.supersupper.repository.DirectionRepository;
import com.heftyb.supersupper.repository.IngredientRepository;
import com.heftyb.supersupper.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImp
    implements RecipeService
{
    @Autowired
    private RecipeRepository recrepo;

    @Autowired
    private DirectionRepository dirrepo;

    @Autowired
    private IngredientRepository ingrepo;


    @Override
    public List<Recipe> findAll()
    {
        List<Recipe> list = new ArrayList<>();

        recrepo.findAll()
            .iterator()
            .forEachRemaining(list::add);

        return list;
    }

    @Override
    public List<Recipe> findByNameContaining(String name)
    {
        return recrepo.findByRecipenameContainingIgnoreCase(name);
    }

    @Override
    public Recipe findRecipeById(long id)
    {
        return recrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + id + " not found!"));
    }

    @Override
    public Recipe save(Recipe recipe)
    {
        Recipe r = new Recipe();

        if (recipe.getRecipieid() != 0)
        {
            recrepo.findById(recipe.getRecipieid())
                .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + recipe.getRecipieid() + " not found!"));
            r.setRecipieid(recipe.getRecipieid());
        }

        r.setRecipename(recipe.getRecipename());
        r.setDescription(recipe.getDescription());

        r.getDirections().clear();

        for(RecipeDirection d : recipe.getDirections())
        {
            Direction di = new Direction();

//            if (d.getDirection().getDirectionid() != 0)
//            {
//                dirrepo.findById(d.getDirection().getDirectionid())
//                    .orElseThrow(() -> new ResourceNotFoundException("Direction id " + d.getDirection().getDirectionid() + " Not Found!"));
//
//                di.setDirectionid(d.getDirection().getDirectionid());
//            }

            di.setDirection(d.getDirection().getDirection());

            di = dirrepo.save(di);

            r.getDirections().add(new RecipeDirection(r, di, d.getStepnum()));
        }

        r.getIngredients()
            .clear();

        for (RecipeIngredient rec : recipe.getIngredients())
        {
            Ingredient i = new Ingredient();

            if (rec.getIngredient().getIngredientid() != 0)
            {
                ingrepo.findById(rec.getIngredient().getIngredientid())
                    .orElseThrow(() -> new ResourceNotFoundException("Ingredient id " + rec.getIngredient().getIngredientid() + " not found!"));

                i.setIngredientid(rec.getIngredient().getIngredientid());
            }
            i.setName(rec.getIngredient().getName());
            i.setDescription(rec.getIngredient().getDescription());
            i.setCategory(rec.getIngredient().getCategory());


            r.getIngredients()
                .add(new RecipeIngredient(r,
                    i, rec.getQty()));
        }

        return recrepo.save(r);
    }

    @Override
    public Recipe update(
        Recipe recipe,
        long id)
    {
        Recipe r = findRecipeById(id);

        if (recipe.getRecipieid() != 0)
        {
            recrepo.findById(recipe.getRecipieid())
                .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + id + " not found!"));
            r.setRecipieid(recipe.getRecipieid());
        }

        if (recipe.getRecipename() != null)
        {
            r.setRecipename(recipe.getRecipename());
        }

        if (recipe.getDescription() != null)
        {
            r.setDescription(recipe.getDescription());
        }

        if (recipe.getDirections().size() > 0)
        {
            r.getIngredients()
                .clear();

            for (RecipeIngredient rec : recipe.getIngredients())
            {
                r.getIngredients()
                    .add(new RecipeIngredient(r,
                        rec.getIngredient(),
                        rec.getQty()));
            }
        }


        if (recipe.getIngredients()
            .size() > 0)
        {
            r.getIngredients()
                .clear();

            for (RecipeIngredient rec : recipe.getIngredients())
            {
                r.getIngredients()
                    .add(new RecipeIngredient(r,
                        rec.getIngredient(),
                        rec.getQty()));
            }
        }


        return recrepo.save(r);
    }

    @Override
    public void delete(long id)
    {
        recrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Recipe id " + id + " not found!"));
        recrepo.deleteById(id);
    }
}
