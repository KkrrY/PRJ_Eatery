package controller;

import configuration.PageSizePropertyHolderMetaData;
import entity.DishOrder;
import entity.Orders;
import entity.UserEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import repository.DishOrderRepository;
import repository.OrderRepository;
import repository.UserRepository;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/recent-orders") // Set a distinct base URL for user-related actions
@SessionAttributes("orders")
public class UserOrderController {

    private final OrderRepository orderRepo;
    private final UserRepository userRepository;
    private final DishOrderRepository dishOrderRepository;
    private PageSizePropertyHolderMetaData props;

    public UserOrderController(OrderRepository orderRepo, UserRepository userRepository, DishOrderRepository dishOrderRepository, PageSizePropertyHolderMetaData props) {
        this.orderRepo = orderRepo;
        this.userRepository = userRepository;
        this.dishOrderRepository = dishOrderRepository;
        this.props = props;
    }

    @GetMapping("/orders")
    public String ordersForUser(Principal principal,
                                @AuthenticationPrincipal UserEntity user, Model model) {

        //TODO: fix user (Use customUserDetails obj) because it should be got from @AuthenticationPrincipal
        user = userRepository.findByUsername(principal.getName());

        Pageable pageable = PageRequest.of(0, props.getPageSize());
        List<Orders> ordersList = orderRepo.findByUserNameOrderByPlacedAtDesc(user.getUsername(), pageable);
        ordersList.forEach(i ->
                i.setDishOrder( dishOrderRepository.findAllByOrder(i)
                )
        );

        model.addAttribute("orders", ordersList );


        return "orderList";
    }


}