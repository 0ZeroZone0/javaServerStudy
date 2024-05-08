package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest userCreateRequest){
        userRepository.save(new User(userCreateRequest.getName(), userCreateRequest.getAge()));
    }

    public List<UserResponse> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public void updateUser(UserUpdateRequest userUpdateRequest){
        // select * from user where id = ?;
        // Optional<User>
        User user = userRepository.findById(userUpdateRequest.getId())
                .orElseThrow(IllegalAccessError::new);

        user.updateName(userUpdateRequest.getName());
        userRepository.save(user);
    }
}
