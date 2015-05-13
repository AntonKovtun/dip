package com.dip.backend.service.module;

import com.dip.common.dto.AuthToken;

import java.util.Map;


public interface IAuthTokenModule {
    AuthToken createToken(Map tokenParam) throws Exception;
    /**
     * Updates the token
     * @param tokenParam
     * @return
     */
    AuthToken refreshToken(Map tokenParam)throws Exception;
    /**
     * Determines if a token is still valid
     * @param userId
     * @param token
     * @return
     */
    boolean isTokenValid(String userId, String principal, String token) throws Exception;

}
