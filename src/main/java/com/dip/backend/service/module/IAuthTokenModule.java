package com.sulin.backend.service.module;

import com.sulin.common.dto.AuthToken;

import java.util.Map;

/**
 * Created by: Alexander Duckardt
 * Date: 8/3/14.
 */
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
