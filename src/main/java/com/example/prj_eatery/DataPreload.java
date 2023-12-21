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

            Dishes taco = new Dishes("TACO");
            taco.setName("taco");
            taco.setDescription("Default taco with flour tortilla, beef, cheese, and spicy tomato sauce");
            taco.setPrice(40.0);
            taco.setImageSrc("https://interactive.bonappetit.com/tacos/Taco-Grid-Tripas.9af29e68.png");
            dishesRepo.save(taco);
            Dishes misoSoup = new Dishes("MISO SOUP");
            misoSoup.setName("Miso-soup");
            misoSoup.setDescription("Japanese soup consisting of a dashi stock into which softened miso paste is mixed");
            misoSoup.setPrice(60.0);
            misoSoup.setImageSrc("https://static.vecteezy.com/system/resources/previews/026/758/030/original/miso-soup-with-ai-generated-free-png.png");
            dishesRepo.save(misoSoup);
            Dishes pizza = new Dishes("PIZZA");
            pizza.setName("Piza");
            pizza.setDescription("Pepperoni pizza, consisting of salami, cheese, tomato sauce");
            pizza.setPrice(60.0);
            pizza.setImageSrc("https://pizza-leopoli.lviv.ua/wp-content/uploads/2021/03/about-02-img-1.png");
            dishesRepo.save(pizza);

        };
    }

}
