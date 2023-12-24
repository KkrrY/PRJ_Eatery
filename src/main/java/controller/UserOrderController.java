package controller;

import configuration.PageSizePropertyHolderMetaData;
import entity.Orders;
import entity.UserEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.OrderRepository;
import repository.UserRepository;
import entity.Orders.Status;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recent-orders") // Set a distinct base URL for user-related actions
@SessionAttributes("orders")
public class UserOrderController {

    private final OrderRepository orderRepo;
    private final UserRepository userRepository;
    private PageSizePropertyHolderMetaData props;

    public UserOrderController(OrderRepository orderRepo, UserRepository userRepository, PageSizePropertyHolderMetaData props) {
        this.orderRepo = orderRepo;
        this.userRepository = userRepository;
        this.props = props;
    }

    @GetMapping("/orders")
    public String ordersForUser(Principal principal,
                                @AuthenticationPrincipal UserEntity user, Model model) {

        //TODO: fix user (Use customUserDetails obj) because it should be got from @AuthenticationPrincipal
        user = userRepository.findByUsername(principal.getName());

        Pageable pageable = PageRequest.of(0, props.getPageSize());
        List<Orders> ordersList = orderRepo.findByUserNameOrderByPlacedAtDesc(user.getUsername(), pageable);

        model.addAttribute("orders", ordersList );


        return "orderList";
    }


    @PostMapping("/cancel-order/{orderId}")
    public String cancelOrder(@PathVariable Long orderId, Model model) {

        Optional<Orders> optionalOrder = orderRepo.findById(String.valueOf(orderId));
        System.out.println(optionalOrder.get().getId());
        
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();

            if (order.getStatus() == Status.OPENED ) {
                order.setStatus(Status.CANCELLED);
                orderRepo.save(order);

                // Update the orders list in the session attribute
                List<Orders> ordersList = (List<Orders>) model.getAttribute("orders");
                ordersList.replaceAll(o -> o.getId().equals(orderId) ? order : o);

                model.addAttribute("orders", ordersList);
            }
        }

        // Redirect to the order list page
        return "redirect:/recent-orders/orders";
    }

}