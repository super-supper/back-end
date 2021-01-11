package com.heftyb.supersupper.repository;

import com.heftyb.supersupper.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long>
{
}
