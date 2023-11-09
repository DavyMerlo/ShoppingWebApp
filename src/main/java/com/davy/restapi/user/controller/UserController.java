package com.davy.restapi.user.controller;

import com.davy.restapi.shared.handler.ResponseHandler;
import com.davy.restapi.authetication.request.ChangePasswordRequest;
import com.davy.restapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<?> findUserById(@PathVariable final Long id){

        var data = userService.findUserById(id);
        return ResponseHandler.generateResponse("successful",  HttpStatus.OK, data);
    }

    @GetMapping("/{userId}/address")
    public ResponseEntity<?> findUserWithAddressByUserId(@PathVariable final Long userId){
        var data = userService.findUserWithAddressByUserId(userId);
        return ResponseHandler.generateResponse("successful",  HttpStatus.OK, data);
    }

    @GetMapping("/{userId}/card")
    public ResponseEntity<?> findUserWithCardByUserId(@PathVariable final Long userId){

        var data = userService.findUserWithCardByUserId(userId);
        return ResponseHandler.generateResponse("successful", HttpStatus.OK, data);
    }

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ){
        userService.changePassword(request, connectedUser);
        return ResponseEntity.accepted().build();
    }
}
