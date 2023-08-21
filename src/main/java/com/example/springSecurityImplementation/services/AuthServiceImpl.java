package com.example.springSecurityImplementation.services;

import com.example.springSecurityImplementation.dto.AuthResponse;
import com.example.springSecurityImplementation.dto.LoginRequest;
import com.example.springSecurityImplementation.dto.RegisterRequest;
import com.example.springSecurityImplementation.models.Role;
import com.example.springSecurityImplementation.models.User;
import com.example.springSecurityImplementation.respositories.AuthRepository;
import com.example.springSecurityImplementation.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .pass(passwordEncoder.encode(request.getPass()))
                .address(request.getAddress())
                .role(Role.USER)
                .build();
        authRepo.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwt).build();
    }

    @Override
    public AuthResponse login(LoginRequest request) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getName(),request.getPass());
        try{
            authManager.authenticate(usernamePasswordAuthenticationToken);

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println();
            throw new Exception("invalid user name or password");
        }

        User user = authRepo.findByName(request.getName())
                .orElseThrow();
        String jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwt).build();
    }
}
