package com.heftyb.supersupper;

import com.heftyb.supersupper.models.*;
import com.heftyb.supersupper.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    DirectionService directionService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        Category c1 = new Category("Meat");
        Category c2 = new Category("Herbs");
        Category c3 = new Category("Spices");
        Category c4 = new Category("Dairy");
        Category c5 = new Category("Fruits");
        Category c6 = new Category("Produce");
        Category c7 = new Category("Frozen");
        Category c8 = new Category("Canned");
        Category c9 = new Category("Pantry");
        Category c10 = new Category("Bread");
        Category c11 = new Category("Other");

        c1 = categoryService.save(c1);
        c2 = categoryService.save(c2);
        c3 = categoryService.save(c3);
        c4 = categoryService.save(c4);
        c5 = categoryService.save(c5);
        c6 = categoryService.save(c6);
        c7 = categoryService.save(c7);
        c8 = categoryService.save(c8);
        c9 = categoryService.save(c9);
        c10 = categoryService.save(c10);
        c11 = categoryService.save(c11);

        Ingredient i1 = new Ingredient("All-Purpose Flour",
            c9);
        Ingredient i2 = new Ingredient("Fresh Ground Pepper",
            c3);
        Ingredient i3 = new Ingredient("Stewing Meat",
            c1);
        Ingredient i4 = new Ingredient("Vegetable Oil",
            c9);
        Ingredient i5 = new Ingredient("Red Wine Vinegar",
            c9);
        Ingredient i6 = new Ingredient("Red Wine",
            c11);
        Ingredient i7 = new Ingredient("Beef Broth",
            c9);
        Ingredient i8 = new Ingredient("Bay Leaves",
            c3);
        Ingredient i9 = new Ingredient("Onions",
            c6);
        Ingredient i10 = new Ingredient("Carrots",
            c6);
        Ingredient i11 = new Ingredient("Potatoes",
            c6);
        Ingredient i12 = new Ingredient("Salt",
            c3);

        i1 = ingredientService.save(i1);
        i2 = ingredientService.save(i2);
        i3 = ingredientService.save(i3);
        i4 = ingredientService.save(i4);
        i5 = ingredientService.save(i5);
        i6 = ingredientService.save(i6);
        i7 = ingredientService.save(i7);
        i8 = ingredientService.save(i8);
        i9 = ingredientService.save(i9);
        i10 = ingredientService.save(i10);
        i11 = ingredientService.save(i11);
        i12 = ingredientService.save(i12);

        Recipe rec1 = new Recipe();

        rec1.setRecipename("Beef Stew");
        rec1.setDescription("A delicious old time stew");

        Direction dir1 = new Direction("Combine the flour and pepper in a bowl, add the beef and toss to coat well.");
        Direction dir2 = new Direction("Heat 3 teaspoons of the oil in a large pot. Add the beef a few pieces at a time; do not overcrowd. Cook, turning the pieces until beef is browned on all sides, about 5 minutes per batch. Add more oil as needed between batches.");
        Direction dir3 = new Direction("Remove the beef from the pot and add the vinegar and wine. Cook over medium-high heat, scraping the pan with a wooden spoon to loosen any browned bits.");
        Direction dir4 = new Direction("Add the beef, beef broth and bay leaves. Bring to a boil, then reduce to a slow simmer.");
        Direction dir5 = new Direction("Cover and cook, skimming broth from time to time, until the beef is tender, about 1 1/2 hours. Add the onions and carrots and simmer, covered, for 10 minutes.");
        Direction dir6 = new Direction("Add the potatoes and simmer until vegetables are tender, about 30 minutes more. Add broth or water if the stew is dry. Season with salt and pepper to taste.");

        dir1 = directionService.save(dir1);
        dir2 = directionService.save(dir2);
        dir3 = directionService.save(dir3);
        dir4 = directionService.save(dir4);
        dir5 = directionService.save(dir5);
        dir6 = directionService.save(dir6);

        rec1.getDirections()
            .add(new RecipeDirection(rec1,
                dir1,
                1));
        rec1.getDirections()
            .add(new RecipeDirection(rec1,
                dir2,
                2));
        rec1.getDirections()
            .add(new RecipeDirection(rec1,
                dir3,
                3));
        rec1.getDirections()
            .add(new RecipeDirection(rec1,
                dir4,
                4));
        rec1.getDirections()
            .add(new RecipeDirection(rec1,
                dir5,
                5));
        rec1.getDirections()
            .add(new RecipeDirection(rec1,
                dir6,
                6));

        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i1,
                "1/4 cup"));
        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i2,
                "1/4 tsp"));
        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i3,
                "1LB"));
        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i4,
                "5 tsp"));
        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i5,
                "2 tbsp"));
        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i6,
                "1 cup"));
        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i7,
                "3 1/2 cups"));
        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i8,
                "2"));
        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i9,
                "1 medium"));
        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i10,
                "5 medium"));
        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i11,
                "3 large"));
        rec1.getIngredients()
            .add(new RecipeIngredient(rec1,
                i12,
                "2 tsp"));

        rec1 = recipeService.save(rec1);
    }
}