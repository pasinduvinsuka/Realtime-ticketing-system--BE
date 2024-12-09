package com.example.ticketingSystem.dto;

public class ResponseDto<T> {
    private int code;
    private String message;
    private T data;

    public ResponseDto(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
