package com.sulin.common.constant;

/**
 * @author Alexander Duckardt
 * 
 */
public enum ErrorCodes {
    // common
    OK("OK"),
    QUEUE_IS_EMPTY("empty"),
    GENERIC_ERROR("GENERIC_ERROR"),
    SESSION_EXPIRED("session.expired"),
    RECORD_NOT_FOUND("record.not.found"),
    VALIDATION_ERROR("VALIDATION_ERROR"),

    AUTH_LOGIN_IS_EMPTY("sulin.auth.error.login.required"),
    AUTH_PASSWORD_IS_EMPTY("sulin.auth.error.password.required"),
    AUTH_USER_NOT_FOUND("sulin.auth.error.user.not.found"),
    AUTH_INVALID_LOGIN_AND_PWD_PAIR("sulin.auth.error.login.password.not.found"),
    AUTH_CANNOT_GENERATE_TOKEN("sulin.auth.error.token.generation.failed");

    private final String value;

    ErrorCodes(String val) {
        value = val;
    }

    public String getErrorCode(){
        return this.value;
    }
}
