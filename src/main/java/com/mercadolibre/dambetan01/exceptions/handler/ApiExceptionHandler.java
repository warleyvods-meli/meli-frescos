package com.mercadolibre.dambetan01.exceptions.handler;

import com.mercadolibre.dambetan01.exceptions.error.BadRequestException;
import com.mercadolibre.dambetan01.exceptions.error.NotFoundException;
import com.mercadolibre.dambetan01.exceptions.error.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(NotFoundException exception) {
        var error = StandardError
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("resource not found")
                .detail(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException exception) {
        var error = StandardError
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("resource not found")
                .detail(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validateField(MethodArgumentNotValidException e) {
        var error = StandardError
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("there is something wrong with your request")
                .detail(e.getAllErrors().get(0).getDefaultMessage())
                .developerMessage(e.getClass().getName())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}


