package com.nhat.ShoppingManagement.security.entity.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRefreshRequest {
//    @NotBlank
//    private String username;
//    @NotBlank
//    private String password;
    @NotBlank
    private String refreshToken;
}