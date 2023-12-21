package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table (name = "Orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //LocalDateTime because Date class is outdated and non-parsed with THYMELEAF
    private LocalDateTime placedAt;

    private Double totalPrice;

    @Column(name = "order_name")
    @NotBlank(message="Delivery( order ) name is required")
    private String orderName;

    @Column(name = "customer_full_name")
    private String customerFullName;

    @Column(name = "customer_username")
    private String userName;

    @NotBlank(message="Street is required")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    private String deliveryCity;

    @NotBlank(message="State is required")
    private String deliveryState;

    @NotBlank(message="Zip code is required")
    private String deliveryZip;

    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;

    @Column(name = "cc_cvv")
    @Digits(integer=3, fraction=0, message="Invalid CVV") //value contains exactly three numeric digits.
    private String ccCVV;

    @ManyToOne
    private UserEntity user;

    @ManyToMany(targetEntity= Burger.class)
    private List<Burger> burgers = new ArrayList<>();


    @ManyToMany(targetEntity= Dishes.class)
    private List<Dishes> dishOrder = new ArrayList<>();
    public void addDish(Dishes dishOrder) {this.dishOrder.add(dishOrder);}

    public void addTaco(Burger design) {
        this.burgers.add(design);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = LocalDateTime.now();
    }

}
