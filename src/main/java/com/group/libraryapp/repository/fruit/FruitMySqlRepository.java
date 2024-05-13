package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.dto.fruit.response.FruitResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@Primary
public class FruitMySqlRepository implements FruitRepository {

    private final JdbcTemplate jdbcTemplate;

    public FruitMySqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveFruit(String name, LocalDate warehousingDate, long price) {
        String sql = "insert into fruit (name, warehousingDate, price) values (?, ?, ?)";
        jdbcTemplate.update(sql, name,warehousingDate, price);
    }


    @Override
    public boolean isFruitNotExist(long id) {
        String readSql = "SELECT * FROM fruit WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateFruit(long id) {
        String sql = "UPDATE fruit SET status = 'SOLD' WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    public boolean isFruitNotExist(String name){
        String readFruitInfo = "SELECT * FROM fruit WHERE name = ?";
        return jdbcTemplate.query(readFruitInfo, (rs, rowNum) -> 0, name).isEmpty();
    }

    public FruitResponse getFruitStatus(String name) {
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
