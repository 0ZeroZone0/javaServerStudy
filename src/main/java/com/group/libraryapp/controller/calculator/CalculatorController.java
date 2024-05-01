package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // CalculatorController클래스를 api진입 지점으로 만들어준다.
public class CalculatorController {

  @GetMapping("/add") // GET /add
  public int addTwoNumbers(CalculatorAddRequest request) {
    return request.getNumber1() + request.getNumber2();
  }

  @PostMapping("/multiply") //POST /multiply
  public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){
    //@RequestBody 있어야지 Post API 에서 정상적으로 HTTP body 안에 담긴 JSON을 CalculatorMultiplyRequest 객체로 변경 가능
    //즉, HTTP Body로 들어오는 JSON을 CalculatorMultiplyRequest 로 바꿈
    return request.getNumber1() * request.getNumber2();
  }

}
