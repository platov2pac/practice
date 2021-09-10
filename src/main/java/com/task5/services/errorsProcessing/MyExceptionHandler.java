package com.task5.services.errorsProcessing;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<AwesomeException> handleNotFoundException() {
        return new ResponseEntity<>(new AwesomeException("There is no such user"), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class AwesomeException {
        private String message;
    }
}
