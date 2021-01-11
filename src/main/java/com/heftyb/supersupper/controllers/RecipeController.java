package com.heftyb.supersupper.controllers;

import com.heftyb.supersupper.models.Recipe;
import com.heftyb.supersupper.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController
{
    @Autowired
    private RecipeService recipeService;

    @GetMapping(value = "/recipes",
        produces = "application/json")
    public ResponseEntity<?> findAllRecipes()
    {
        List<Recipe> list = recipeService.findAll();
        return new ResponseEntity<>(list,
            HttpStatus.OK);
    }

    @GetMapping(value = "/recipe/{id}",
        produces = "application/json")
    public ResponseEntity<?> findRecipeById(
        @PathVariable
            long id)
    {
        Recipe r = recipeService.findRecipeById(id);
        return new ResponseEntity<>(r,
            HttpStatus.OK);
    }

    @GetMapping(value = "/recipie/name/{subname}",
        produces = "application/json")
    public ResponseEntity<?> findByName(
        @PathVariable
            String subname)
    {
        List<Recipe> list = recipeService.findByNameContaining(subname);
        return new ResponseEntity<>(list,
            HttpStatus.OK);
    }

    @PostMapping(value = "/recipe",
        consumes = "application/json")
    public ResponseEntity<?> addRecipe(
        @Valid
        @RequestBody
            Recipe recipe) throws URISyntaxException
    {
        recipe.setRecipieid(0);
        recipe = recipeService.save(recipe);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRecipeURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{recipeid}")
            .buildAndExpand(recipe.getRecipieid())
            .toUri();
        responseHeaders.setLocation(newRecipeURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    @PutMapping(value = "/recipe/{id}",
        consumes = "application/json")
    public ResponseEntity<?> updateFullRecipe(
        @Valid
        @RequestBody
            Recipe updateRecipe,
        @PathVariable
            long id)
    {
        updateRecipe.setRecipieid(id);
        recipeService.save(updateRecipe);

        return new ResponseEntity<>(null,
            HttpStatus.OK);
    }

    @PatchMapping(value = "recipe/{id}",
        consumes = "application/json")
    public ResponseEntity<?> updateRecipe(
        @RequestBody
            Recipe updateRecipe,
        @PathVariable
            long id)
    {
        recipeService.update(updateRecipe,
            id);
        return new ResponseEntity<>(null,
            HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/recipe/{id}")
    public ResponseEntity<?> deleteRecipe(
        @PathVariable
            long id)
    {
        recipeService.delete(id);
        return new ResponseEntity<>(null,
            HttpStatus.OK);
    }
}
