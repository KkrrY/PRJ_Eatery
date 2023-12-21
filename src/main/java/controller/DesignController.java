package controller;

import entity.Burger;
import entity.Ingredient;
import entity.Orders;
import entity.UserEntity;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import repository.IngredientRepository;
import entity.Ingredient.Type;
import repository.UserRepository;
import service.DiscountService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@Slf4j
public class DesignController {

    private final IngredientRepository ingredientRepo;
    private UserRepository userRepo;

    @Autowired
    public DesignController(
            IngredientRepository ingredientRepo,
            UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public Orders order() {
        Orders order = new Orders();
        order.setUserName("guest");
        return order;
    }

    @ModelAttribute(name = "burger")
    public Burger burger() {
        return new Burger();
    }

    @ModelAttribute(name = "user")
    public UserEntity user(Principal principal) {

        String guestRole = "Guest";
        String username = principal == null ? guestRole : principal.getName() ;
        UserEntity user;
        if (username.equals(guestRole)) {
        user = new UserEntity("Guest", "Guest", "Guest", "Guest", "Guest", "Guest", "Guest", "Guest");
        user.setRole("GUEST");
        }
        else user = userRepo.findByUsername(username);
        return user;
    }


    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processDish(
            @Valid Burger burger, Errors errors,
            @SessionAttribute Orders order) {


        if (errors.hasErrors()) {
            return "design";
        }

        order.addTaco(burger);
        order.setTotalPrice(
                (  order.getTotalPrice() == null ? 0 : order.getTotalPrice()   ) +
                (  burger.getIngredients().stream()
                .mapToDouble(i -> i.getPrice())
                .sum()  * DiscountService.calculateDiscount() )
        );

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}

