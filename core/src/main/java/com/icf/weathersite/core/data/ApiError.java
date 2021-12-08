package com.icf.weathersite.core.data;

public class ApiError {
    private Integer errorCode;
    private String errorMessage;

    //default constructor should be explicitly codes when you override a constructor
    public ApiError() {
    }

    public ApiError(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
