package com.fishingstore.User.Service;

import com.fishingstore.Exception.DomainException;
import com.fishingstore.User.Model.User;
import com.fishingstore.User.Model.UserRole;
import com.fishingstore.User.Repository.UserRepository;
import com.fishingstore.Web.Dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User register(RegisterRequest registerRequest) {

        Optional<User> byUsernameOrEmail = userRepository.findByUsernameOrEmail(registerRequest.getUsername(), registerRequest.getEmail());

        if(byUsernameOrEmail.isPresent()) {
            throw new DomainException("Username already exists");
        }

        return userRepository.save(initializeUser(registerRequest));

    }

    private User initializeUser(RegisterRequest registerRequest) {

        return User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .isActive(true)
                .role(UserRole.USER)
                .build();
    }


    public UserDetails loadUserByUsername(String username) {
        return (UserDetails) userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
