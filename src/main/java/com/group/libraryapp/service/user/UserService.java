package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(JdbcTemplate jdbcTemplate) {
        userRepository = new UserRepository(jdbcTemplate);
    }

    public void saveUser(UserCreateRequest userCreateRequest){
        userRepository.saveUser(userCreateRequest.getName(), userCreateRequest.getAge());
    }

    public List<UserResponse> getUsers(){
        return userRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest userUpdateRequest){
        if(userRepository.isUserNotExist(userUpdateRequest.getId())){
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(userUpdateRequest.getName(), userUpdateRequest.getId());
    }

    public void deleteUser(String name){
        if(userRepository.isUserNotExist(name)){
            throw new IllegalArgumentException();
        }

        userRepository.deleteUser(name);
    }



}
