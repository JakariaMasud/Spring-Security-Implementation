package com.example.springSecurityImplementation.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {
    private  String name;
    private String pass;
}
