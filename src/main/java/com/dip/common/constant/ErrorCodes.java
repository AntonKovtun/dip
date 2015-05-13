package com.dip.common.constant;


public enum ErrorCodes {
    // common
    OK("OK"),
    QUEUE_IS_EMPTY("empty"),
    GENERIC_ERROR("GENERIC_ERROR"),
    SESSION_EXPIRED("session.expired"),
    RECORD_NOT_FOUND("record.not.found"),
    VALIDATION_ERROR("VALIDATION_ERROR"),

    AUTH_LOGIN_IS_EMPTY("dip.auth.error.login.required"),
    AUTH_PASSWORD_IS_EMPTY("dip.auth.error.password.required"),
    AUTH_USER_NOT_FOUND("dip.auth.error.user.not.found"),
    AUTH_INVALID_LOGIN_AND_PWD_PAIR("dip.auth.error.login.password.not.found"),
    AUTH_CANNOT_GENERATE_TOKEN("dip.auth.error.token.generation.failed");

    private final String value;

    ErrorCodes(String val) {
        value = val;
    }

    public String getErrorCode(){
        return this.value;
    }
}
