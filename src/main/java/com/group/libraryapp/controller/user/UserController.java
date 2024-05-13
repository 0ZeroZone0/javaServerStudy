package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.fruit.FruitService;
import com.group.libraryapp.service.fruit.FruitServiceClass;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
  private final UserServiceV2 userService;
  private final FruitServiceClass fruitService;

  public UserController(UserServiceV2 userService, @Qualifier("main") FruitServiceClass fruitService){
    this.userService = userService;
    this.fruitService = fruitService;
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

