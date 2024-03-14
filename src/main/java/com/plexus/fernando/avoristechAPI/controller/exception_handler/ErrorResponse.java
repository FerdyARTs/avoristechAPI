package com.plexus.fernando.avoristechAPI.controller.exception_handler;


public class  ErrorResponse {

    private int httpStatus;

    private String type, message, clazz, method;

    private int line;

    public ErrorResponse() {
    }

    public ErrorResponse(int httpStatus, String type, String message, String clazz, String method, int line) {
        this.httpStatus = httpStatus;
        this.type = type;
        this.message = message;
        this.clazz = clazz;
        this.method = method;
        this.line = line;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
