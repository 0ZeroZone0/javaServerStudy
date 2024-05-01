package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.Fruit;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

  //private final List<User> users = new ArrayList<>();
  private final JdbcTemplate jdbcTemplate; //자바 데이터베이스 커넥터에 대한 클래스. JDBC 구현

  public UserController(JdbcTemplate jdbcTemplate){ // JDBC 템플릿을 받아서 생성자를 만듦.
    this.jdbcTemplate = jdbcTemplate;
  }

  @PostMapping("/user") // POST /user
  public void saveUser(@RequestBody UserCreateRequest request) {
    //새로운 User를 만들어서 api에 들어온 이름과 나이를 넣어준다.
    //users.add(new User(request.getName(), request.getAge()));

    String sql = "INSERT INTO user(name, age) VALUES (?,?)";
    jdbcTemplate.update(sql, request.getName(),request.getAge());

  }

  //테스트용
  @GetMapping("/fruit")
  public Fruit fruit(){
    return new Fruit("바나나", 2000);
  }

  @GetMapping("/user")
  public List<UserResponse> getUser(){
    /*
    List<UserResponse> responses = new ArrayList<>();

    for(int i = 0; i < users.size(); i++){
      responses.add(new UserResponse(i+1, users.get(i)));
    }

    return responses;
  */

    String sql = "SELECT * FROM user";
    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      long id = rs.getLong("id");
      String name = rs.getString("name");
      int age = rs.getInt("age");
      return new UserResponse(id, name, age);
    });
  }


}

