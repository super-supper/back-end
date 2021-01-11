package com.heftyb.supersupper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends Auditable
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryid;

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "category",
        allowSetters = true)
    private List<Ingredient> ingredients;

    public Category()
    {
    }

    public Category(@NotNull String name)
    {
        this.name = name;
    }

    public Category(
        @NotNull String name,
        List<Ingredient> ingredients)
    {
        this.name = name;
        this.ingredients = ingredients;
    }

    public long getCategoryid()
    {
        return categoryid;
    }

    public void setCategoryid(long categoryid)
    {
        this.categoryid = categoryid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
    }

    @Override
    public String toString()
    {
        return "Category{" +
            "name='" + name + '\'' +
            ", ingredients=" + ingredients +
            '}';
    }
}
