package com.heftyb.supersupper.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "directions")
public class Direction extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long directionid;


    @NotNull
    private String direction;

    public Direction()
    {
    }

    public Direction(
        @NotNull String direction)
    {
        this.direction = direction;
    }

    public long getDirectionid()
    {
        return directionid;
    }

    public void setDirectionid(long directionid)
    {
        this.directionid = directionid;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    @Override
    public String toString()
    {
        return "Direction{" +
            ", direction='" + direction + '\'' +
            '}';
    }
}
