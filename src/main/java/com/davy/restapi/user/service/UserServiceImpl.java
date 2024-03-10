package com.davy.restapi.user.service;

import com.davy.restapi.shared.validators.RequestValidator;
import com.davy.restapi.user.entity.UserEntity;
import com.davy.restapi.user.request.ChangePasswordRequest;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.user.mapper.UserAddressMapper;
import com.davy.restapi.user.mapper.userCardMapper;
import com.davy.restapi.user.mapper.UserItemsMapper;
import com.davy.restapi.user.repository.UserRepository;
import com.davy.restapi.user.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserItemsMapper userItemsMapper;
    private final UserAddressMapper addressMapper;
    private final userCardMapper userCardMapper;
    private final RequestValidator<ChangePasswordRequest> changePasswordRequestValidatorImpl;

    @Override
    public UserListResponse findAllUsers() {
        if(userRepository.findAll().isEmpty()) ThrowException.objectException("Users");
        return UserListResponse.builder()
                .users(userRepository.findAll()
                        .stream()
                        .map(userItemsMapper)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public UserPassWordResponse changePassword(ChangePasswordRequest request, Principal connectedUser) {
        changePasswordRequestValidatorImpl.validate(request);
        var user = (UserEntity) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return UserPassWordResponse.builder()
                .message("Password successfully changed")
                .build();
    }

    @Override
    public UserResponse findUserById(Long id) {
        if(userRepository.findById(id).isEmpty()) ThrowException.objectByIdException(id, "User");
        return UserResponse.builder()
                .user(userRepository.findById(id)
                        .map(userItemsMapper)
                        .stream()
                        .findFirst()
                        .get())
                .build();
    }

    @Override
    public UserAddressResponse findUserWithAddressByUserId(Long id){
        if(userRepository.findById(id).isEmpty()) ThrowException.objectByIdException(id, "User");
        return UserAddressResponse.builder()
                .user(userRepository.findById(id)
                        .stream()
                        .map(addressMapper)
                        .findFirst()
                        .get())
                .build();
    }

    @Override
    public UserCardResponse findUserWithCardByUserId(Long userId) {
        if(userRepository.findById(userId).isEmpty()) ThrowException.objectByIdException(userId, "User");
        return UserCardResponse.builder()
                .user(userRepository
                        .findById(userId)
                        .map(userCardMapper)
                        .stream()
                        .findFirst()
                        .get())
                .build();
    }
}
