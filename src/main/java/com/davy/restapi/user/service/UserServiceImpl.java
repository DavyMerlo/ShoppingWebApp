package com.davy.restapi.user.service;

import com.davy.restapi.authetication.request.ChangePasswordRequest;
import com.davy.restapi.shared.exceptions.ThrowException;
import com.davy.restapi.user.entity.User;
import com.davy.restapi.user.mapper.UserAddressItemsMapper;
import com.davy.restapi.user.mapper.UserCardItemsMapper;
import com.davy.restapi.user.mapper.UserItemsMapper;
import com.davy.restapi.user.repository.UserRepository;
import com.davy.restapi.user.response.UserAddressResponse;
import com.davy.restapi.user.response.UserCardResponse;
import com.davy.restapi.user.response.UserListResponse;
import com.davy.restapi.user.response.UserResponse;
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
    private final UserAddressItemsMapper addressMapper;
    private final UserCardItemsMapper userCardItemsMapper;

    @Override
    public UserListResponse findAllUsers() {
        var response = new UserListResponse();
        if(userRepository.findAll().isEmpty()){
            ThrowException.objectException("Users");
        }
        response.setUsers(userRepository.findAll()
                .stream()
                .map(userItemsMapper)
                .collect(Collectors.toList()));
        return response;
    }

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }
        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        // save the new password
        userRepository.save(user);
    }

    @Override
    public UserResponse findUserById(Long id) {

        UserResponse response = new UserResponse();
        if(userRepository.findById(id).isEmpty()){
            ThrowException.objectByIdException(id, "User");
        }
        response.setUser(userRepository.findById(id)
                .stream()
                .map(userItemsMapper)
                .findFirst()
                .get());
        return response;
    }

    @Override
    public UserAddressResponse findUserWithAddressByUserId(Long id){

        UserAddressResponse response = new UserAddressResponse();
        if(userRepository.findById(id).isEmpty()){
            ThrowException.objectByIdException(id, "User");
        }
        response.setUser(userRepository.findById(id)
                .stream()
                .map(addressMapper)
                .findFirst()
                .get());
        return response;
    }

    @Override
    public UserCardResponse findUserWithCardByUserId(Long userId) {

        UserCardResponse response = new UserCardResponse();
        if(userRepository.findById(userId).isEmpty()){
            ThrowException.objectByIdException(userId, "User");
        }
        response.setUser(userRepository.findById(userId)
                .stream()
                .map(userCardItemsMapper)
                .findFirst()
                .get());
        return response;
    }
}
