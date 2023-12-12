package com.davy.restapi.authetication.service;

import com.davy.restapi.address.entity.Address;
import com.davy.restapi.authetication.confirmationtoken.ConfirmationToken;
import com.davy.restapi.authetication.confirmationtoken.ConfirmationTokenService;
import com.davy.restapi.authetication.response.AuthenticationResponse;
import com.davy.restapi.authetication.request.RegisterRequest;
import com.davy.restapi.authetication.request.AuthenticationRequest;
import com.davy.restapi.authetication.response.RefreshTokenResponse;
import com.davy.restapi.authetication.response.RegisterResponse;
import com.davy.restapi.card.entity.CustomerCard;
import com.davy.restapi.shared.validators.RequestValidator;
import com.davy.restapi.shared.validators.RequestValidatorImpl;
import com.davy.restapi.token.entity.Token;
import com.davy.restapi.address.repository.AddressRepository;
import com.davy.restapi.card.repository.CardRepository;
import com.davy.restapi.token.repository.TokenRepository;
import com.davy.restapi.token.enums.TokenType;
import com.davy.restapi.user.enums.Role;
import com.davy.restapi.user.entity.User;
import com.davy.restapi.user.mapper.UserItemsMapper;
import com.davy.restapi.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CardRepository cardRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtServiceImp;
    private final AuthenticationManager authenticationManager;
    private final RequestValidator<AuthenticationRequest> authenticationRequestValidatorImpl;
    private final RequestValidator<RegisterRequest> requestRequestValidatorImpl;
    private final UserItemsMapper userItemsMapper;
    private final ConfirmationTokenService confirmationTokenService;

    public RegisterResponse register(RegisterRequest request) {
        requestRequestValidatorImpl.validate(request);

        Address address = createAddress(request);
        CustomerCard customerCard = createCustomerCard();
        User user = createUser(request, address, customerCard);

        addressRepository.save(address);
        var savedUser = userRepository.save(user);

        cardRepository.save(customerCard);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now().plusMinutes(15),
                null,
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return RegisterResponse.builder()
                .confirmationResponse(token)
                .user(userItemsMapper.apply(savedUser))
                .build();
    }

    @Override
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        userRepository.enableAppUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationRequestValidatorImpl.validate(request);
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
               var authResponse = RefreshTokenResponse.builder()
                       .accessToken(accessToken)
                       .refreshToken(refreshToken)
                       .build();
                System.out.println(authResponse.getRefreshToken());
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
                .role(Role.MANAGER)
                .enabled(false)
                .locked(false)
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
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .address(address)
                .enabled(false)
                .locked(false)
                .build();
    }
}
