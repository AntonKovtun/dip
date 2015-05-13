package com.dip.common.dto.response;

import com.dip.common.constant.ErrorCodes;
import com.dip.common.constant.ResponseStatus;

import java.io.Serializable;

public class ServiceResponse implements Serializable {

    protected ResponseStatus status;
    protected ErrorCodes errorCode;
    protected String errorText;

    public ServiceResponse() {

    }

    public ServiceResponse(ResponseStatus s) {
        status = s;
    }

    public void fail() {
        this.status = ResponseStatus.FAILURE;
    }
    public void succeed() {
        this.status = ResponseStatus.SUCCESS;
    }
    public boolean isSuccess() {
        return ResponseStatus.SUCCESS.equals(status);
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodes errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
