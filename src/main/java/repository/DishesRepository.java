package repository;

import entity.Dishes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface DishesRepository
extends CrudRepository<Dishes, String> {
    Page<Dishes> findAll(Pageable pageable);
    @NonNull
    Optional<Dishes> findById (String id);
}
