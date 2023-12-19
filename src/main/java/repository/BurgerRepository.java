package repository;

import entity.Burger;
import org.springframework.data.repository.CrudRepository;

public interface BurgerRepository
        extends CrudRepository<Burger, Long> {

}
