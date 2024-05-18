package com.group.libraryapp.service.fruit;


import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.dto.fruit.request.FruitRequest;
import com.group.libraryapp.dto.fruit.response.FruitCountResponse;
import com.group.libraryapp.dto.fruit.response.FruitInfoResponse;
import com.group.libraryapp.dto.fruit.response.FruitResponse;
import com.group.libraryapp.repository.fruit.FruitARepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitService {

    private final FruitARepository fruitRepository;

    public FruitService(FruitARepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void saveFruit(FruitRequest request) {
        fruitRepository.save(new Fruit(request.getName(), request.getWarehousingDate(), request.getPrice()));
    }

    public void updateFruit(FruitRequest request) {
        Fruit fruit = fruitRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        fruit.updateSoldStatus();
        fruitRepository.save(fruit);
    }

    public FruitResponse getFruitStatus(String name) {
        long salesAmount = fruitRepository.findAllByNameAndStatus(name, Fruit.Status.SOLD).stream()
                .mapToLong(Fruit::getPrice)
                .sum();

        long notSalesAmount = fruitRepository.findAllByNameAndStatus(name, Fruit.Status.NOT_SOLD).stream()
                .mapToLong(Fruit::getPrice)
                .sum();

        return new FruitResponse(salesAmount, notSalesAmount);
    }


    public FruitCountResponse getFruitCount(String name) {
        return new FruitCountResponse(fruitRepository.countByName(name));
    }

    public List<FruitInfoResponse> getFruitsByPrice(String option, long price) {
        List<Fruit> fruits;

        if (option.equals("GTE")) {
            fruits = fruitRepository.findByPriceGreaterThanEqual(price);
        } else if (option.equals("LTE")) {
            fruits = fruitRepository.findByPriceLessThanEqual(price);
        } else {
            throw new IllegalArgumentException();
        }

        return fruits.stream()
                .filter(fruit -> fruit.getStatus() == Fruit.Status.NOT_SOLD) // 판매되지 않은 상태를 확인한다.
                .map(fruit -> new FruitInfoResponse(fruit.getName(), fruit.getPrice(), fruit.getWarehousingDate()))
                .collect(Collectors.toList());
    }
}
