package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FruitARepository extends JpaRepository<Fruit, Long> {
    Optional<Fruit> findByName(String name);
    List<Fruit> findAllByNameAndStatus(String name, Fruit.Status status);
    long countByName(String name);
    List<Fruit> findByPriceGreaterThanEqual(long price);
    List<Fruit> findByPriceLessThanEqual(long price);

}
