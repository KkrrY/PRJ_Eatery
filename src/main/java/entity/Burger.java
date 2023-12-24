package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

/*
Ingredients are the essential building blocks of a taco. To capture how those ingredients
are brought together, we’ll define the dish domain class, as shown next.
*/

@Data
@Entity
@Table(name = "Burgers")
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    @Column(length = 20)
    private String name;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;

    @ManyToOne(targetEntity = Orders.class)
    private Orders order;

    public void addIngredient(Ingredient ingredient) { //S26 change (adding new method)
        this.ingredients.add(ingredient);
    }
}
