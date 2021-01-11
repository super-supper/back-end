package com.heftyb.supersupper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientid;

    @NotNull
    @Column(unique = true)
    private String name;

    private String description;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "categoryid")
    @JsonIgnoreProperties(value = "ingredients",
        allowSetters = true)
    private Category category;

    @OneToMany(mappedBy = "ingredient",
        cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "ingredient",
        allowSetters = true)
    private List<RecipeIngredient> recipes = new ArrayList<>();

    public Ingredient()
    {
    }

    public Ingredient(
        @NotNull String name,
        @NotNull Category category)
    {
        this.name = name;
        this.category = category;
    }

    public Ingredient(
        @NotNull String name,
        String description,
        @NotNull Category category)
    {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public long getIngredientid()
    {
        return ingredientid;
    }

    public void setIngredientid(long ingredientid)
    {
        this.ingredientid = ingredientid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public List<RecipeIngredient> getRecipes()
    {
        return recipes;
    }

    public void setRecipes(List<RecipeIngredient> recipes)
    {
        this.recipes = recipes;
    }

    @Override
    public String toString()
    {
        return "Ingredient{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", category=" + category +
            '}';
    }
}
