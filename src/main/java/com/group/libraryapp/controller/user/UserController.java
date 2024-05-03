package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  //private final List<User> users = new ArrayList<>();
  private final UserService userService ;
  private final JdbcTemplate jdbcTemplate; //자바 데이터베이스 커넥터에 대한 클래스. JDBC 구현

  public UserController(JdbcTemplate jdbcTemplate){ // JDBC 템플릿을 받아서 생성자를 만듦.
    this.jdbcTemplate = jdbcTemplate;
    this.userService = new UserService(jdbcTemplate);
  }

  @PostMapping("/user") // POST /user
  public void saveUser(@RequestBody UserCreateRequest request) {
    userService.saveUser(request);
  }

  @GetMapping("/user")
  public List<UserResponse> getUser(){
    return userService.getUsers();
  }

  @PutMapping("/user")
  public void updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
    userService.updateUser(userUpdateRequest);
  }

  @DeleteMapping("/user")
  public void deleteUser(@RequestParam String name){
    userService.deleteUser(name);
  }




}

