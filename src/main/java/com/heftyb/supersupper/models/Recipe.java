package com.heftyb.supersupper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipieid;

    @NotNull
    private String recipename;

    private String description;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "recipe")
    private Set<RecipeDirection> directions = new HashSet<>();

    @OneToMany(mappedBy = "recipe",
        cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "recipe",
        allowSetters = true)
    private Set<RecipeIngredient> ingredients = new HashSet<>();

    public Recipe()
    {
    }

    public Recipe(
        @NotNull String recipename,
        String description)
    {
        this.recipename = recipename;
        this.description = description;
    }

    public Recipe(
        @NotNull String recipename,
        String description,
        Set<RecipeDirection> directions,
        Set<RecipeIngredient> ingredients)
    {
        this.recipename = recipename;
        this.description = description;
        this.directions = directions;
        this.ingredients = ingredients;
    }

    public long getRecipieid()
    {
        return recipieid;
    }

    public void setRecipieid(long recipieid)
    {
        this.recipieid = recipieid;
    }

    public String getRecipename()
    {
        return recipename;
    }

    public void setRecipename(String recipename)
    {
        this.recipename = recipename;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<RecipeDirection> getDirections()
    {
        return directions;
    }

    public void setDirections(Set<RecipeDirection> directions)
    {
        this.directions = directions;
    }

    public Set<RecipeIngredient> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(Set<RecipeIngredient> ingredients)
    {
        this.ingredients = ingredients;
    }

    @Override
    public String toString()
    {
        return "Recipe{" +
            "recipename='" + recipename + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
