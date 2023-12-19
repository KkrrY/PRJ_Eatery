package repository;

import entity.DishOrder;
import entity.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DishOrderRepository extends CrudRepository<DishOrder,String> {
    List<DishOrder> findAllByOrder (Orders order);
}
