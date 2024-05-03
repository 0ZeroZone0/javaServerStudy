package com.group.libraryapp.repository;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //해당 id로 조회된 데이터 있는지 확인
    public boolean isUserNotExist(long id){
        String readSql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();

    }

    //업데이트
    public void updateUserName(String name, long id){
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    public boolean isUserNotExist(String name){
        //조회
        String readSql = "SELECT * FROM user WHERE name = ?";
        //해당 name 로 조회된 데이터 있는지 확인
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public void deleteUser(String name){
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public void saveUser(String name, Integer age){
        String sql = "INSERT INTO user(name, age) VALUES (?,?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> getUsers(){
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

}
