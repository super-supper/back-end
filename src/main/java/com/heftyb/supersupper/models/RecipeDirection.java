package com.heftyb.supersupper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "recipedirection")
@IdClass(RecipeDirection.class)
public class RecipeDirection extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "recipeid")
    @JsonIgnoreProperties(value = "directions", allowSetters = true)
    private Recipe recipe;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "directionid")
    @JsonIgnoreProperties(value = "recipes", allowSetters = true)
    private Direction direction;

    @NotNull
    private int stepnum;

    public RecipeDirection()
    {
    }

    public RecipeDirection(
        @NotNull Recipe recipe,
        @NotNull Direction direction,
        @NotNull int stepnum)
    {
        this.recipe = recipe;
        this.direction = direction;
        this.stepnum = stepnum;
    }

    public Recipe getRecipe()
    {
        return recipe;
    }

    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public int getStepnum()
    {
        return stepnum;
    }

    public void setStepnum(int stepnum)
    {
        this.stepnum = stepnum;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof RecipeDirection))
        {
            return false;
        }
        RecipeDirection that = (RecipeDirection) o;
        return ((recipe == null) ? 0 : recipe.getRecipieid()) == ((that.recipe == null) ? 0 : that.recipe.getRecipieid()) &&
            ((direction == null) ? 0 : direction.getDirectionid()) == ((that.direction == null) ? 0 : that.direction.getDirectionid());
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
