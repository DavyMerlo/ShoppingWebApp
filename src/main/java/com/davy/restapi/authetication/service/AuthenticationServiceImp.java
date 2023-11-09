package com.davy.restapi.authetication.service;

import com.davy.restapi.address.entity.Address;
import com.davy.restapi.authetication.response.AuthenticationResponse;
import com.davy.restapi.authetication.request.RegisterRequest;
import com.davy.restapi.authetication.request.AuthenticationRequest;
import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.shared.validators.RequestValidator;
import com.davy.restapi.token.entity.Token;
import com.davy.restapi.address.repository.AddressRepository;
import com.davy.restapi.card.repository.CardRepository;
import com.davy.restapi.token.repository.TokenRepository;
import com.davy.restapi.token.enums.TokenType;
import com.davy.restapi.user.dto.UserItems;
import com.davy.restapi.user.enums.Role;
import com.davy.restapi.user.entity.User;
import com.davy.restapi.user.mapper.UserItemsMapper;
import com.davy.restapi.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CardRepository cardRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImp jwtServiceImp;
    private final AuthenticationManager authenticationManager;
    private final RequestValidator<AuthenticationRequest> authenticationRequestValidator;
    private final RequestValidator<RegisterRequest> requestRequestValidator;
    private final UserItemsMapper userItemsMapper;

    public AuthenticationResponse register(RegisterRequest request) {
        requestRequestValidator.validate(request);

        //Create address of the user
        Address address = createAddress(request);

        //Create customer card
        CustomerCard customerCard = createCustomerCard();

        //Create the user
        User user = createUser(request, address, customerCard);

        //Save the address of the user
        addressRepository.save(address);

        //Save the user
        var savedUser = userRepository.save(user);

        //Save the customer card
        cardRepository.save(customerCard);

        //Generate token by user
        var jwtToken = jwtServiceImp.generateToken(user);

        //Generate refresh token by user
        var refreshToken = jwtServiceImp.generateRefreshToken(user);

        //Save user token by the saved user and the generated jwt token
        saveUserToken(savedUser, jwtToken);

        //return response: access and refresh token
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(userItemsMapper.apply(savedUser))
                .build();
    }

    private Address createAddress(RegisterRequest request){
        return Address.builder()
                .street(request.getStreet())
                .houseNumber(request.getHouseNumber())
                .busNumber(request.getBusNumber())
                .localAuthority(request.getLocalAuthority())
                .postalCode(request.getPostalCode())
                .build();
    }

    private CustomerCard createCustomerCard(){
        return CustomerCard.builder()
                .points((byte) 0)
                .number("1515151515151515")
                .build();
    }

    private User createUser(RegisterRequest request, Address address, CustomerCard customerCard){
        return User.builder()
                .customerCard(customerCard)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .address(address)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationRequestValidator.validate(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtServiceImp.generateToken(user);
        var refreshToken = jwtServiceImp.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(userItemsMapper.apply(user))
                .build();
    }

    //

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtServiceImp.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtServiceImp.isTokenValid(refreshToken, user)) {
               var accessToken = jwtServiceImp.generateToken(user);
               revokeAllUserTokens(user);
               saveUserToken(user, accessToken);
               var authResponse = AuthenticationResponse.builder()
                       .accessToken(accessToken)
                       .refreshToken(refreshToken)
                       .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if(validUserTokens.isEmpty()){
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public User createDemoUser(){
        User DemoUser = User.builder()
                .firstname("Davy")
                .lastname("Merlo")
                .email("davymerlo@live.be")
                .password(passwordEncoder.encode("Merlo1988"))
                .role(Role.CUSTOMER)
                .address(Address.builder()
                        .street("Rootstraat")
                        .houseNumber("30")
                        .busNumber("1")
                        .localAuthority("Maasmechelen")
                        .postalCode("3630")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
                .customerCard(CustomerCard.builder()
                        .points((byte) 10)
                        .number("15151515151515")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return userRepository.save(DemoUser);
    }
}
