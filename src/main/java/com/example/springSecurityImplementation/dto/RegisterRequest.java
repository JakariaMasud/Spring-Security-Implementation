package com.example.springSecurityImplementation.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {
    private String name;
    private String pass;
    private String address;

}
