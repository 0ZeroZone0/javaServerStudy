package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.dto.fruit.response.FruitResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class FruitMemoryRepository implements FruitRepository {

    public void saveFruit(String name, LocalDate warehousingDate, long price) {}

    @Override
    public boolean isFruitNotExist(long id) {
        return false;
    }

    @Override
    public boolean isFruitNotExist(String name) {
        return false;
    }

    public void updateFruit(long id) {}

    public FruitResponse getFruitStatus(String name) {
        return null;
    }
}
