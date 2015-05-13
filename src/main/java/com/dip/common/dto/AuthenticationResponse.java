package com.dip.common.dto;

import com.dip.common.constant.ResponseStatus;
import com.dip.common.dto.response.ServiceResponse;


public class AuthenticationResponse extends ServiceResponse {

    private long expirationTime;
    private int resultCode;

    private AuthToken authToken;

    private String userId;
    private String principal;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(ResponseStatus s) {
        super(s);
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

}
