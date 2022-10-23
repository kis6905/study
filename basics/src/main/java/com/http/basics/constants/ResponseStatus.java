package com.http.basics.constants;

public enum ResponseStatus {
    OK("200", "OK"),
    NOT_FOUND("404", "NOT_FOUND"),
    ;

    private String statusCode;
    private String statusMessage;

    ResponseStatus(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
