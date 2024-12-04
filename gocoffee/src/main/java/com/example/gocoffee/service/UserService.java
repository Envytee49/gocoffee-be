package com.example.gocoffee.service;

import com.example.gocoffee.dto.request.user.UserRequest;
import com.example.gocoffee.dto.response.user.UserInfoResponse;
import com.example.gocoffee.model.User;

import java.util.List;

public interface UserService {
    UserInfoResponse getUserInfo();

    User updateProfile(UserRequest dto);

    User getUserById(Long id);

    User addUser(UserRequest dto);

    User updateUser(UserRequest dto);

    List<User> deleteUser(List<Long> idList);
}
