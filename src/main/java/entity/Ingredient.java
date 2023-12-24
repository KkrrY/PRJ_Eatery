package entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "Ingredients")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED, force=true)
public class Ingredient {
    @Column(unique = true)
    @Id
    private final String id;
    @Column(length = 20)
    private final String name;
    @Enumerated(EnumType.STRING)
    private final Type type;
    @Column(precision = 5)
    private final Double price;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
