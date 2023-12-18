package com.example.UserAuthenticationService.security;

import com.example.UserAuthenticationService.domain.User;

public interface SecurityTokenGenerator {

    String createToken(User user);
}
