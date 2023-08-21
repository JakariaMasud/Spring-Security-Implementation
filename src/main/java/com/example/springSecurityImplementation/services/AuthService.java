package com.example.springSecurityImplementation.services;

import com.example.springSecurityImplementation.dto.AuthResponse;
import com.example.springSecurityImplementation.dto.LoginRequest;
import com.example.springSecurityImplementation.dto.RegisterRequest;


public interface AuthService {


    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request) throws Exception;
}
