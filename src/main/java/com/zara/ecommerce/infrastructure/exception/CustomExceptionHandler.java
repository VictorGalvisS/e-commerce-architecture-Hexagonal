package com.zara.ecommerce.infrastructure.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(SocketTimeoutException.class)
    public ResponseEntity<Object> handleTimeOutException(Exception ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                "ERROR, PLEASE COME BACK LATER OR TRY WITH ANOTHER DATA: " +
                        ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class, MethodArgumentNotValidException.class, JsonMappingException.class,
            DateTimeParseException.class})
    protected ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                "FAILED, PLEASE TRY WITH ANOTHER DATA: " +
                        ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InformationNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                "ERROR NOT FOUND PARAMETERS, PLEASE TRY WITH ANOTHER DATA: " +
                        ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleInternalServerErrorException(Exception ex, WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                "ERROR PLEASE COME BACK LATER OR TRY WITH ANOTHER DATA. THANKS: " +
                        ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}