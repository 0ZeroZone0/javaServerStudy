package com.group.libraryapp.dto.fruit.response;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FruitInfoResponse {
    private String name;

    private Long price;

    private LocalDate warehousingDate;

    public FruitInfoResponse(String name, long price, LocalDate warehousingDate) {
        this.name = name;
        this.price = price;
        this.warehousingDate =warehousingDate;
    }
}
