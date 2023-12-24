package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "dishes")
@NoArgsConstructor(access= AccessLevel.PUBLIC)
public class Dishes { //A class ( JPA table ) made for storing ready-made taco`s user can buy
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) //digit datatype doesn't work here because records in db will duplicate
    //forbidden to change id name because jpa searches for id column name
    @NonNull
    private String id;

    @Column(unique = true)
    @NotNull
    @Size(min=1, message="Name must be at least 5 characters long")
    private String name;

    @Column(precision = 5)
    private Double price;

    private Date createdAt = new Date();

    @Lob
    private String description;

    private String imageSrc;

    //Non-necessary relation (they're set one-sided as usual)
//    @ManyToMany(targetEntity = Orders.class)
//    private List<Orders> orders;

    public Dishes(String id) {
        this.id = id;
    }
}
