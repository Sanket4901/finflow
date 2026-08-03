package com.finflow.service;

import com.finflow.dto.request.LoginRequest;
import com.finflow.dto.request.RegisterRequest;
import com.finflow.dto.response.LoginResponse;
import com.finflow.dto.response.RegisterResponse;
import com.finflow.entity.User;
import com.finflow.enums.Role;
import com.finflow.exception.EmailAlreadyExistsException;
import com.finflow.exception.InvalidCredentialsException;
import com.finflow.repository.UserRepository;
import com.finflow.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

//    public AuthServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    @Override
    public RegisterResponse register(RegisterRequest request) {

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }

            User user = new User();

            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .message("User registered successfully")
                .build();

    }
    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException("Invalid email or password");
        }
        String token = jwtService.generateToken(user.getEmail());
        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .message("Login successful")
                .build();
    }
}