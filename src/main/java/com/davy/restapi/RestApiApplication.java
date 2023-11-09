package com.davy.restapi;

import com.davy.restapi.authetication.service.AuthenticationServiceImp;
import com.davy.restapi.user.entity.User;
import com.davy.restapi.authetication.service.JwtServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@AllArgsConstructor
public class RestApiApplication implements CommandLineRunner{

    JwtServiceImp jwtServiceImp;
    AuthenticationServiceImp authenticationServiceImp;

    public static void main(String[] args){
        SpringApplication.run(RestApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var user = demoUser();
        var jwtToken = generateJwtToken(user);
        var refreshToken = generateRefreshToken(user);
        saveUserToken(user, jwtToken);
        printCustomerTokenOnConsole(
                user.getFirstname(),
                user.getLastname(),
                jwtToken,
                refreshToken);
    }

    private void saveUserToken(User user,
                               String jwtToken) {
        authenticationServiceImp.saveUserToken(user, jwtToken);
    }

    private User demoUser(){
        return authenticationServiceImp.createDemoUser();
    }

    private void printCustomerTokenOnConsole(String firstname,
                                            String lastname,
                                            String jwtToken,
                                            String refreshToken){
        System.out.println("Demo Customer: " + firstname + " " + lastname);
        System.out.println("JwtToken: " + jwtToken);
        System.out.println("RefreshToken: " + refreshToken);
    }

    private String generateJwtToken(User user){
        return jwtServiceImp.generateToken(user);
    }

    private String generateRefreshToken(User user){
        return jwtServiceImp.generateRefreshToken(user);
    }
}
