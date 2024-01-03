package com.davy.restapi.authetication.controller;

import com.davy.restapi.authetication.request.AuthenticationRequest;
import com.davy.restapi.authetication.service.AuthenticationService;
import com.davy.restapi.authetication.request.RegisterRequest;
import com.davy.restapi.shared.handler.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request){
        var data = service.register(request);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request){
        var data = service.authenticate(request);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }

    @PostMapping("refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping(path = "confirm/{token}")
    public  ResponseEntity<?> confirm(@PathVariable String token){
        var data = service.confirmToken(token);
        messagingTemplate.convertAndSend("/topic/status", data.isConfirmed());
        return ResponseHandler.generateResponse(true, HttpStatus.OK, data);
    }
}
