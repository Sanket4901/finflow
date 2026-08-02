package com.finflow.service;

import com.finflow.dto.request.RegisterRequest;
import com.finflow.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

}