package com.davy.restapi.user.service;

import com.davy.restapi.user.entity.User;
import com.davy.restapi.user.request.ChangePasswordRequest;
import com.davy.restapi.user.response.UserListResponse;
import com.davy.restapi.user.response.UserResponse;

import java.security.Principal;

public interface UserService {
    Object findUserWithCardByUserId(Long userId);
    Object findUserWithAddressByUserId(Long id);
    Object changePassword(ChangePasswordRequest request, Principal connectedUser);
    UserResponse findUserById(Long id);
    UserListResponse findAllUsers();
}
