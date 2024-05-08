package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.dto.fruit.request.FruitRequest;
import com.group.libraryapp.dto.fruit.response.FruitResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class FruitController {

    private final JdbcTemplate jdbcTemplate;
    public FruitController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/api/v1/fruit")
    public void saveFruit(@RequestBody FruitRequest request) {
        String sql = "insert into fruit (name, warehousingDate, price) values (?, ?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice());
    }

    @PutMapping("/api/v1/fruit")
    public void updateFruit(@RequestBody FruitRequest request) {
        //먼저 과일이 있는지 체크
        String readSql = "SELECT * FROM fruit WHERE id = ?";
        boolean isFruitNotExit = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();

        //존재 하지않는다면 예외처리
        if (isFruitNotExit) {
            throw new IllegalStateException();
        }

        //있으면 업데이트
        String sql = "UPDATE fruit SET status = 'SOLD' WHERE id = ?";
        jdbcTemplate.update(sql, request.getId());
    }


    @GetMapping("/api/v1/fruit/status")
    public FruitResponse getFruitStatus(@RequestParam String name) {

        // 과일 이름을 기준으로 데이터베이스에서 과일 정보를 조회
        String readFruitInfo = "SELECT * FROM fruit WHERE name = ?";
        // 해당 과일이 존재하지 않는 경우 예외 처리
        boolean isFruitNotExist = jdbcTemplate.query(readFruitInfo, (rs, rowNum) -> 0, name).isEmpty();
        if (isFruitNotExist) {
            throw new IllegalStateException();
        }

        // 과일이 판매된 총액을 조회
        String soldSql = "SELECT COALESCE(SUM(price), 0) FROM fruit WHERE name = ? AND status = 'SOLD'";
        // 과일이 판매되지 않은 총액을 조회
        String notSoldSql = "SELECT COALESCE(SUM(price), 0) FROM fruit WHERE name = ? AND status = 'NOT_SOLD'";

        // 조회 결과를 각 변수에 저장
        long salesAmount = jdbcTemplate.queryForObject(soldSql, Long.class, name);
        long notSalesAmount = jdbcTemplate.queryForObject(notSoldSql, Long.class, name);

        // 조회된 금액 정보를 반환
        return new FruitResponse(salesAmount, notSalesAmount);
    }

}