package controller;

import entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.DishesRepository;
import repository.UserRepository;
import service.DiscountService;
import service.impl.DiscountServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dishes")
@SessionAttributes("order")
@Slf4j
public class DishesController {

    private final DishesRepository dishesRepo;
    private final UserRepository userRepo;

    private final DiscountService discountService;
    @Autowired
    public DishesController(
            DishesRepository dishesRepo,
            UserRepository userRepo,
            DiscountService discountService) {
        this.dishesRepo = dishesRepo;
        this.userRepo = userRepo;
        this.discountService = discountService;
    }

    @ModelAttribute
    public void addDishesToModel(Model model) {
        List<Dishes> dishes = new ArrayList<>();
        dishesRepo.findAll().forEach(i -> dishes.add(i));

        model.addAttribute("dishes", dishes );
    }


    @ModelAttribute(name = "dish")
    public Dishes dish() {
        return new Dishes();
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
                        ( dish.getPrice() * discountService.calculateDiscount())
        );

        return "redirect:/orders/current";
    }

    

}
