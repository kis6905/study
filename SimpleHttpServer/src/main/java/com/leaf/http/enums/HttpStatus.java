package com.leaf.http.enums;

public enum HttpStatus {
    OK(200),

    BAD_REQUEST(400),
    NOT_FOUND(404),

    INTERNAL_SERVER_ERROR(500),
    ;

    private int status;
    HttpStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

}
