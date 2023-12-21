package controller;

import authorization.CustomUserDetails;
import entity.UserEntity;
import entity.Orders;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import repository.DishesRepository;
import repository.OrderRepository;
import repository.BurgerRepository;
import service.DiscountService;


@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;
    private BurgerRepository burgerRepo;
    private DishesRepository dishesRepo;

    public OrderController(OrderRepository orderRepo, BurgerRepository burgerRepo, DishesRepository dishesRepo) {
        this.orderRepo = orderRepo;
        this.burgerRepo = burgerRepo;
        this.dishesRepo = dishesRepo;

    }

    @ModelAttribute(name = "discount")
    public String showDiscount () {
        return String.valueOf( Math.round( (1 - DiscountService.calculateDiscount() ) * 100 ) );
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal CustomUserDetails user,
                            @SessionAttribute Orders order) {

        if (user != null) {
            order.setUserName(user.getUsername());
            if (order.getCustomerFullName() == null) {
                order.setCustomerFullName(user.getFullName());
            }
            if (order.getDeliveryStreet() == null) {
                order.setDeliveryStreet(user.getStreet());
            }
            if (order.getDeliveryCity() == null) {
                order.setDeliveryCity(user.getCity());
            }
            if (order.getDeliveryState() == null) {
                order.setDeliveryState(user.getCountry());
            }
            if (order.getDeliveryZip() == null) {
                order.setDeliveryZip(user.getZip());
            }
        }

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order") Orders order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal UserEntity user) {

        if (errors.hasErrors()) {
            return "orderForm";
        }
        order.getBurgers().forEach(i -> burgerRepo.save(i)); //Did to avoid org.hibernate.TransientObjectException: object references an unsaved transient instance - save the transient instance before flushing: entity.Burger
        order.getDishOrder().forEach(i -> dishesRepo.save(i));

        order.setUser(user);
        orderRepo.save(order);
        order.getBurgers().forEach(i -> i.setOrder(order) ); //update
        burgerRepo.saveAll(order.getBurgers());
        dishesRepo.saveAll(order.getDishOrder());
        sessionStatus.setComplete();

        return "redirect:/";
    }

}