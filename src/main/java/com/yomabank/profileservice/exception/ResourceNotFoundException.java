package com.yomabank.profileservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private String detailMessage = "Request resource is not found!";
    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException() {
        super();
    }
}
