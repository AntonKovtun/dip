package com.sulin.common.exception;


import com.sulin.common.constant.ErrorCodes;

/**
 * @author Duckardt
 *
 */
public class BasicServiceException extends Exception {
    private static final long serialVersionUID = 1L;
    private ErrorCodes errorCode;
    private String responseValue;
    private Throwable originalCause;

    public BasicServiceException(final ErrorCodes code) {
        this.errorCode = code;
    }

    public BasicServiceException(final ErrorCodes code, final Throwable originalCause) {
        this.errorCode = code;
        this.originalCause = originalCause;
    }

    public BasicServiceException(final ErrorCodes code, final String responseValue) {
        this.errorCode = code;
        this.responseValue = responseValue;
    }

    /**
     * @return the errorCode
     */
    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(ErrorCodes errorCode) {
        this.errorCode = errorCode;
    }

    public String getResponseValue() {
        return responseValue;
    }

    public Throwable getOriginalCause() {
        return originalCause;
    }

}
