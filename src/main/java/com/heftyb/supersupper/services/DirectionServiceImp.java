package com.heftyb.supersupper.services;

import com.heftyb.supersupper.exceptions.ResourceNotFoundException;
import com.heftyb.supersupper.models.Direction;
import com.heftyb.supersupper.repository.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "directionService")
public class DirectionServiceImp implements DirectionService
{
    @Autowired
    private DirectionRepository dirrepo;

    @Override
    public List<Direction> findAll()
    {
        List<Direction> list = new ArrayList<>();

        dirrepo.findAll().iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Direction findDirectionById(long id)
    {
        return dirrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Direction id " + id + " not found!"));
    }

    @Override
    public Direction save(Direction direction)
    {
        Direction d = new Direction();

        if (direction.getDirectionid() != 0)
        {
            dirrepo.findById(direction.getDirectionid())
                .orElseThrow(() -> new ResourceNotFoundException("Direction id " + direction.getDirectionid() + " not found!"));
            d.setDirectionid(direction.getDirectionid());
        }

        d.setDirection(direction.getDirection());

        return dirrepo.save(d);
    }

    @Override
    public void delete(long id)
    {

    }
}
