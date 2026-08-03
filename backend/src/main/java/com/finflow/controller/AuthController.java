package com.finflow.controller;


import com.finflow.dto.request.LoginRequest;
import com.finflow.dto.request.RegisterRequest;
import com.finflow.dto.response.LoginResponse;
import com.finflow.dto.response.RegisterResponse;
import com.finflow.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(
            @Valid @RequestBody RegisterRequest request) {

        return authService.register(request);
    }
    @GetMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }
}
