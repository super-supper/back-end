package com.heftyb.supersupper.controllers;

import com.heftyb.supersupper.models.Ingredient;
import com.heftyb.supersupper.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController
{
    @Autowired
    private IngredientService ingredientService;

    @GetMapping(value = "/ingredients",
        produces = "application/json")
    public ResponseEntity<?> findAllIngredients()
    {
        List<Ingredient> list = ingredientService.findAll();
        return new ResponseEntity<>(list,
            HttpStatus.OK);
    }

    @GetMapping(value = "/ingredient/{id}",
        produces = "application/json")
    public ResponseEntity<?> findRecipeById(
        @PathVariable
            long id)
    {
        Ingredient i = ingredientService.findIngredientById(id);
        return new ResponseEntity<>(i,
            HttpStatus.OK);
    }

    @GetMapping(value = "/name/{subname}",
        produces = "application/json")
    public ResponseEntity<?> findRecipeByName(
        @PathVariable
            String subname)
    {
        List<Ingredient> list = ingredientService.findByNameContaining(subname);
        return new ResponseEntity<>(list,
            HttpStatus.OK);
    }

    @PostMapping(value = "/ingredient",
        consumes = "application/json")
    public ResponseEntity<?> addIngredient(
        @Valid
        @RequestBody
            Ingredient ingredient)
    {
        ingredient.setIngredientid(0);
        ingredient = ingredientService.save(ingredient);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRecipeURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{ingredientid}")
            .buildAndExpand(ingredient.getIngredientid())
            .toUri();
        responseHeaders.setLocation(newRecipeURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    @PutMapping(value = "/ingredient/{id}",
        consumes = "application/json")
    public ResponseEntity<?> updateFullIngredient(
        @Valid
        @RequestBody
            Ingredient updateIngredient,
        @PathVariable
            long id)
    {
        updateIngredient.setIngredientid(id);
        ingredientService.save(updateIngredient);

        return new ResponseEntity<>(null,
            HttpStatus.CREATED);
    }

    @PatchMapping(value = "/ingredient/{id}",
        consumes = "application/json")
    public ResponseEntity<?> updateIngredient(
        @RequestBody
            Ingredient updateIngredient,
        @PathVariable
            long id)
    {
        updateIngredient.setIngredientid(id);
        ingredientService.update(updateIngredient,
            id);

        return new ResponseEntity<>(null,
            HttpStatus.OK);
    }

    @DeleteMapping(value = "/ingredient/{id}")
    public ResponseEntity<?> deleteIngredient(
        @PathVariable
            long id)
    {
        ingredientService.delete(id);
        return new ResponseEntity<>(null,
            HttpStatus.OK);
    }
}
