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

            Dishes taco1 = new Dishes("TACO");
            taco1.setName("taco");
            taco1.setIngredients(Arrays.asList(
                    flourTortilla, groundBeef, carnitas,
                    sourCream, salsa, cheddar));
            taco1.setPrice(taco1.getIngredients().stream().mapToDouble(Ingredient::getPrice).sum() );
            taco1.setImageSrc("https://interactive.bonappetit.com/tacos/Taco-Grid-Tripas.9af29e68.png");
            dishesRepo.save(taco1);
            Dishes taco2 = new Dishes("MISO SOUP");
            taco2.setName("Miso-soup");
            taco2.setIngredients(Arrays.asList(
                    cornTortilla, groundBeef, cheddar,
                    jack, sourCream));
            taco2.setPrice(taco2.getIngredients().stream().mapToDouble(Ingredient::getPrice).sum() );
            taco2.setImageSrc("https://static.vecteezy.com/system/resources/previews/026/758/030/original/miso-soup-with-ai-generated-free-png.png");
            dishesRepo.save(taco2);
            Dishes taco3 = new Dishes("PIZZA");
            taco3.setName("Piza");
            taco3.setIngredients(Arrays.asList(
                    flourTortilla, cornTortilla, tomatoes,
                    lettuce, salsa));
            taco3.setPrice(taco3.getIngredients().stream().mapToDouble(Ingredient::getPrice).sum() );
            taco3.setImageSrc("https://pizza-leopoli.lviv.ua/wp-content/uploads/2021/03/about-02-img-1.png");
            dishesRepo.save(taco3);

        };
    }

}
