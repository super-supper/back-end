package com.heftyb.supersupper.services;

import com.heftyb.supersupper.models.Direction;

import java.util.List;

public interface DirectionService
{
    List<Direction> findAll();

    Direction findDirectionById(long id);

    Direction save(Direction direction);

    void delete(long id);
}
