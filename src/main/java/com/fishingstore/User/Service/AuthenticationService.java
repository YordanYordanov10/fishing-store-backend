package com.fishingstore.User.Service;

import com.fishingstore.Exception.DomainException;
import com.fishingstore.Exception.UserAlreadyExistsException;
import com.fishingstore.User.Model.User;
import com.fishingstore.User.Model.UserRole;
import com.fishingstore.User.Repository.UserRepository;
import com.fishingstore.Web.Dto.AuthenticationRequest;
import com.fishingstore.Web.Dto.AuthenticationResponse;
import com.fishingstore.Web.Dto.RegisterRequest;
import com.fishingstore.security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {

        Optional<User> existingUser = userRepository.findByUsernameOrEmail(request.getUsername(), request.getEmail());

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Username or email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(true)
                .role(UserRole.USER)
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user.getUsername());
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .message("User registered successfully")
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwtToken = jwtService.generateToken(user.getUsername());
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .message("User authenticated successfully")
                .build();
    }
}
