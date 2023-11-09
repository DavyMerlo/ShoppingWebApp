package com.davy.restapi.user.service;

import com.davy.restapi.authetication.request.ChangePasswordRequest;
import com.davy.restapi.user.response.UserListResponse;

import java.security.Principal;

public interface UserService {
    Object findUserWithCardByUserId(Long userId);
    Object findUserWithAddressByUserId(Long id);
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
    Object findUserById(Long id);
    UserListResponse findAllUsers();
}
