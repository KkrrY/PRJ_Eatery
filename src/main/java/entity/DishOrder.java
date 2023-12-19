package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "dish")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class DishOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Dishes.class)
    private final Dishes dish;

    @ManyToOne(targetEntity = Orders.class)
    private final Orders order;
}
