package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.dto.fruit.request.FruitRequest;
import com.group.libraryapp.dto.fruit.response.FruitResponse;
import com.group.libraryapp.service.fruit.FruitService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/fruit")
@RestController
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping
    public void saveFruit(@RequestBody FruitRequest request) {
        fruitService.saveFruit(request);
    }

    @PutMapping
    public void updateFruit(@RequestBody FruitRequest request) {
        fruitService.updateFruit(request);
    }

    @GetMapping("/status")
    public FruitResponse getFruitStatus(@RequestParam String name) {
        return fruitService.getFruitStatus(name);
    }

}