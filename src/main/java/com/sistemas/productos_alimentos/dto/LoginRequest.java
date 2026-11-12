package com.sistemas.productos_alimentos.dto;



import lombok.Data;

@Data
public class LoginRequest {
    private String userId;
    private String password;
}

