package repository;

import entity.UserEntity;
import entity.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Orders, String> {
    List<Orders> findByUserNameOrderByPlacedAtDesc(String userName, Pageable pageable);
}
