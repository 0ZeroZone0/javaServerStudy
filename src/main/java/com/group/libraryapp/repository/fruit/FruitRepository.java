package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.dto.fruit.response.FruitResponse;

import java.time.LocalDate;
import java.util.List;


public interface FruitRepository {

    void saveFruit(String name, LocalDate warehousingDate, long price);

    boolean isFruitNotExist(long id);

    boolean isFruitNotExist(String name);

    void updateFruit(long id);

    FruitResponse getFruitStatus(String name);
}
