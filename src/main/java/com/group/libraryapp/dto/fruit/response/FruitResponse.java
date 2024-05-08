package com.group.libraryapp.dto.fruit.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FruitResponse {

    private long salesAmount;       // 판매된 과일의 총 가격
    private long notSalesAmount;    // 판매되지 않은 과일의 총 가격

}