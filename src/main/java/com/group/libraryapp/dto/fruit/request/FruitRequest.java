package com.group.libraryapp.dto.fruit.request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FruitRequest {

    private long id;
    private String name;
    private LocalDate warehousingDate;
    private long price;

}