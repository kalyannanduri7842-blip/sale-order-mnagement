package com.enterprisepro.erp.service;

import com.enterprisepro.erp.dto.JwtAuthResponse;
import com.enterprisepro.erp.dto.LoginRequest;
import com.enterprisepro.erp.dto.RegisterRequest;
import com.enterprisepro.erp.dto.UserDto;

public interface AuthService {
    JwtAuthResponse login(LoginRequest loginRequest);
    UserDto register(RegisterRequest registerRequest);
    JwtAuthResponse refreshToken(String refreshToken);
    void requestPasswordReset(String email);
    void resetPassword(String token, String newPassword);
    UserDto getCurrentUser();
}
