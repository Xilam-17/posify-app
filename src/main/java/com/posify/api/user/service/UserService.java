package com.posify.api.user.service;

import com.posify.api.user.model.Users;
import com.posify.api.user.repository.UserRepository;
import com.posify.api.user.request.UserRequest;
import com.posify.api.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse register(UserRequest request) {
        Users user = Users.mapToEntity(request);

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);

        Users savedUser = userRepository.save(user);
        return UserResponse.mapToDto(savedUser);
    }
}