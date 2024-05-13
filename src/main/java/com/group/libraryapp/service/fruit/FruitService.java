package com.group.libraryapp.service.fruit;


import com.group.libraryapp.dto.fruit.request.FruitRequest;
import com.group.libraryapp.dto.fruit.response.FruitResponse;
import com.group.libraryapp.repository.fruit.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void saveFruit(FruitRequest request) {
        fruitRepository.saveFruit(request.getName(), request.getWarehousingDate(), request.getPrice());
    }

    public void updateFruit(FruitRequest request) {
        if (fruitRepository.isFruitNotExist(request.getId())) {
            throw new IllegalArgumentException("과일을 찾을 수 없습니다");
        }
        fruitRepository.updateFruit(request.getId());
    }

    public FruitResponse getFruitStatus(String name) {
        if(fruitRepository.isFruitNotExist(name)){
            throw new IllegalArgumentException();
        }

        return fruitRepository.getFruitStatus(name);
    }
}
