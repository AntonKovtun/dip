package com.sulin.frontend.web.model;

import java.io.Serializable;

import com.sulin.common.constant.ErrorCodes;

/**
 * @author Alexander Duckardt
 * 
 */
public class CommonResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private ErrorCodes errorCode = ErrorCodes.OK;
    private T value;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the errorCode
     */
    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(ErrorCodes errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CommonResponse [errorCode=").append(errorCode).append(", value=")
                .append(value != null ? value.toString() : "null").append("]");
        return sb.toString();
    }

}
