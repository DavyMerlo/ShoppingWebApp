package com.davy.restapi.user.controller;

import com.davy.restapi.shared.handler.ResponseHandler;
import com.davy.restapi.user.request.ChangePasswordRequest;
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

    @GetMapping()
    public ResponseEntity<?> findAllUsers(){

        var data = userService.findAllUsers();
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findUserById(@PathVariable final Long id){

        var data = userService.findUserById(id);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<?> findUserWithAddressByUserId(@PathVariable final Long id){
        var data = userService.findUserWithAddressByUserId(id);
        return ResponseHandler.generateResponse(true,  HttpStatus.OK, data);
    }

    @GetMapping("/{userId}/card")
    public ResponseEntity<?> findUserWithCardByUserId(@PathVariable final Long userId){

        var data = userService.findUserWithCardByUserId(userId);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PatchMapping()
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ){
        var data = userService.changePassword(request, connectedUser);
        return ResponseHandler.generateResponse(true, HttpStatus.CREATED, data);
    }
}
