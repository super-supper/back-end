package com.heftyb.supersupper.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecipeDirectionId implements Serializable
{
    private long recipe;

    private long direction;

    public RecipeDirectionId()
    {
    }

    public long getRecipe()
    {
        return recipe;
    }

    public void setRecipe(long recipe)
    {
        this.recipe = recipe;
    }

    public long getDirection()
    {
        return direction;
    }

    public void setDirection(long direction)
    {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        RecipeDirectionId that = (RecipeDirectionId) o;
        return recipe == that.recipe &&
            direction == that.direction;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(recipe,
            direction);
    }
}
