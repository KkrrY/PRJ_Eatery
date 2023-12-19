package com.example.prj_eatery;

import entity.Dishes;
import entity.Ingredient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import repository.DishesRepository;
import repository.IngredientRepository;
import entity.Ingredient.Type;


import java.util.Arrays;

@Component
public class DataPreload {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo, DishesRepository dishesRepo ) {
        return args -> {

            Ingredient flourTortilla = new Ingredient(
                    "FLTO", "Flour buns", Type.WRAP, 5.0);
            Ingredient cornTortilla = new Ingredient(
                    "COTO", "Corn buns", Type.WRAP, 6.0);
            Ingredient groundBeef = new Ingredient(
                    "GRBF", "Ground Beef", Type.PROTEIN, 15.0);
            Ingredient chicken = new Ingredient(
                    "CHCK", "Chicken", Type.PROTEIN, 11.0);
            Ingredient meatNone = new Ingredient(
                    "NOMT", "No meat", Type.PROTEIN, 0.0);
            Ingredient carnitas = new Ingredient(
                    "CARN", "Carnitas", Type.PROTEIN, 18.0);
            Ingredient tomatoes = new Ingredient(
                    "TMTO", "Diced Tomatoes", Type.VEGGIES, 6.0);
            Ingredient lettuce = new Ingredient(
                    "GRKS", "Gurkeys", Type.VEGGIES, 5.0);
            Ingredient cheddar = new Ingredient(
                    "CHED", "Cheddar", Type.CHEESE, 8.0);
            Ingredient jack = new Ingredient(
                    "JACK", "Monterrey Jack", Type.CHEESE, 9.0);
            Ingredient salsa = new Ingredient(
                    "SLSA", "Salsa", Type.SAUCE, 5.0);
            Ingredient sourCream = new Ingredient(
                    "SRCR", "Sour Cream", Type.SAUCE, 6.0);
            Ingredient ketchup = new Ingredient(
                    "KTCH", "Ketchup", Type.SAUCE, 4.0);
            repo.save(flourTortilla);
            repo.save(cornTortilla);
            repo.save(groundBeef);
            repo.save(carnitas);
            repo.save(chicken);
            repo.save(meatNone);
            repo.save(tomatoes);

            repo.save(lettuce);
            repo.save(cheddar);
            repo.save(jack);
            repo.save(salsa);
            repo.save(sourCream);
            repo.save(ketchup);

            Dishes taco1 = new Dishes("CARNIVORE");
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    flourTortilla, groundBeef, carnitas,
                    sourCream, salsa, cheddar));
            taco1.setPrice(taco1.getIngredients().stream().mapToDouble(Ingredient::getPrice).sum() );
            if (!dishesRepo.existsById(taco1.getId())) dishesRepo.save(taco1);
            Dishes taco2 = new Dishes("BOVBOUNTY");
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(
                    cornTortilla, groundBeef, cheddar,
                    jack, sourCream));
            taco2.setPrice(taco2.getIngredients().stream().mapToDouble(Ingredient::getPrice).sum() );
            if (!dishesRepo.existsById(taco2.getId())) dishesRepo.save(taco2);
            Dishes taco3 = new Dishes("VEGOUT");
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(
                    flourTortilla, cornTortilla, tomatoes,
                    lettuce, salsa));
            taco3.setPrice(taco3.getIngredients().stream().mapToDouble(Ingredient::getPrice).sum() );
            if (!dishesRepo.existsById(taco3.getId())) dishesRepo.save(taco3);

        };
    }

}
