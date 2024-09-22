package com.services.marketplace.service;

import com.services.marketplace.model.JwtResponse;
import com.services.marketplace.model.LoginRequest;
import com.services.marketplace.model.RegisterRequest;

public interface IAuthService {

    JwtResponse login(LoginRequest request);

    JwtResponse register(RegisterRequest request);

}
