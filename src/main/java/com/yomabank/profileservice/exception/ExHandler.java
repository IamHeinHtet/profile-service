package com.yomabank.profileservice.exception;

import com.yomabank.profileservice.datatransferobject.global.ErrorBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    protected ResponseEntity<ErrorBody> handleExceptionInternal(
            RuntimeException ex, WebRequest request) {
        ErrorBody error;
        if(ex instanceof ResourceNotFoundException) {
            error = new ErrorBody(((ResourceNotFoundException) ex).getDetailMessage(),((ResourceNotFoundException) ex).getHttpStatus());
        } else {
            error = new ErrorBody("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ex.printStackTrace();
        return new ResponseEntity<>(error,error.getHttpStatus());
    }
}
