package com.dip.backend.service;

import com.dip.common.dto.AuthenticationResponse;

public interface IAuthenticationService {
    public AuthenticationResponse login(String principal, String password);
    public void logout(String userId);
    public AuthenticationResponse renewToken(String principal, String token, String usId);
}
