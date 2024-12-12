package com.example.ticketingSystem.dto;

public class ResponseDto<T> {
    private int code;
    private boolean isError;
    private String message;
    private T data;

    public ResponseDto(int code, boolean isError, String message, T data) {
        this.code = code;
        this.isError = isError;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
