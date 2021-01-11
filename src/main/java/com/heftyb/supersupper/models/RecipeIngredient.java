package com.heftyb.supersupper.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "recipeingredient")
@IdClass(RecipeIngredientId.class)
public class RecipeIngredient extends Auditable implements Serializable
{

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = "ingredients",
        allowSetters = true)
    private Recipe recipe;


    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "ingredientid")
    @JsonIgnoreProperties(value = "recipes",
        allowSetters = true)
    private Ingredient ingredient;

    private String qty;

    public RecipeIngredient()
    {
    }

    public RecipeIngredient(@NotNull Ingredient ingredient)
    {
        this.ingredient = ingredient;
    }

    public RecipeIngredient(
        @NotNull Recipe recipe,
        @NotNull Ingredient ingredient,
        String qty)
    {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.qty = qty;
    }


    public Recipe getRecipe()
    {
        return recipe;
    }

    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    public Ingredient getIngredient()
    {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient)
    {
        this.ingredient = ingredient;
    }

    public String getQty()
    {
        return qty;
    }

    public void setQty(String qty)
    {
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof RecipeIngredient))
        {
            return false;
        }
        RecipeIngredient that = (RecipeIngredient) o;
        return ((recipe == null) ? 0 : recipe.getRecipieid()) == ((that.recipe == null) ? 0 : that.recipe.getRecipieid()) &&
            ((ingredient == null) ? 0 : ingredient.getIngredientid()) == ((that.ingredient == null) ? 0 : that.ingredient.getIngredientid());
    }

    @Override
    public int hashCode()
    {
        // return Objects.hash(user.getUserid(), role.getRoleid());
        return 37;
    }
}
