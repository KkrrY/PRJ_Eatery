package controller;

import entity.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import repository.DishesRepository;
import repository.IngredientRepository;
import repository.UserRepository;
import service.DiscountService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dishes")
@SessionAttributes("order")
@Slf4j
public class DishesController {

    private final DishesRepository dishesRepo;
    private final UserRepository userRepo;

    @Autowired
    public DishesController(
            DishesRepository dishesRepo,
            UserRepository userRepo) {
        this.dishesRepo = dishesRepo;
        this.userRepo = userRepo;
    }

    @ModelAttribute
    public void addDishesToModel(Model model) {
        List<Dishes> dishes = new ArrayList<>();
        dishesRepo.findAll().forEach(i -> dishes.add(i));

        model.addAttribute("dishes", dishes );
    }

    @ModelAttribute(name = "order")
    public Orders order() {
        Orders order = new Orders();
        order.setUserName("guest");
        return order;
    }

    @ModelAttribute(name = "dish")
    public Dishes dish() {
        return new Dishes();
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
    public String showMapping() {
        return "dishes";
    }

    @PostMapping
    public String processDish(
            @RequestParam String dishId,
            //@Valid Dishes dish, Errors errors,
            @SessionAttribute Orders order) {

//        if (errors.hasErrors()) {
//            return "dishes";
//        }
        Dishes dish = dishesRepo.findById(dishId).orElseThrow(() -> new IllegalArgumentException("Invalid dish id"));
        order.addDish(dish);
        order.setTotalPrice(
                (  order.getTotalPrice() == null ? 0 : order.getTotalPrice()   ) +
                        ( dish.getPrice() * DiscountService.calculateDiscount())
        );

        return "redirect:/orders/current";
    }

    

}
