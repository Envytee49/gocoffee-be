package com.example.gocoffee.service.impl;

import com.example.gocoffee.dto.request.user.UserRequest;
import com.example.gocoffee.model.User;
import com.example.gocoffee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public User getUserInfo() {
        return null;
    }

    @Override
    public User updateProfile(final UserRequest dto) {
        return null;
    }

    @Override
    public User getUserById(final Long id) {
        return null;
    }

    @Override
    public User addUser(final UserRequest dto) {
        return null;
    }

    @Override
    public User updateUser(final UserRequest dto) {
        return null;
    }

    @Override
    public List<User> deleteUser(final List<Long> idList) {
        return List.of();
    }
}
