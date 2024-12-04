package com.example.gocoffee.service.impl;

import com.example.gocoffee.dto.request.user.UserRequest;
import com.example.gocoffee.dto.response.user.UserInfoResponse;
import com.example.gocoffee.exception.UnauthorizedException;
import com.example.gocoffee.model.User;
import com.example.gocoffee.repository.UserRepository;
import com.example.gocoffee.service.UserService;
import com.example.gocoffee.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserInfoResponse getUserInfo() {
        User user = userRepository.findByEmail(SecurityUtil.getUserEmail()).orElseThrow(() -> new UnauthorizedException("User Not Found"));
        return UserInfoResponse.builder()
                .name(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .profileUrl(user.getProfileUrl())
                .build();
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
