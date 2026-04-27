package com.productservice.exceptions;

import com.productservice.exceptions.exceptions.CategoryAlreadyPresentException;
import com.productservice.exceptions.exceptions.CategoryNotFoundException;
import com.productservice.exceptions.exceptions.FakeStoreException;
import com.productservice.exceptions.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    private final ExceptionResponseDto response = new ExceptionResponseDto();

    @ExceptionHandler(FakeStoreException.class)
    public ResponseEntity<ExceptionResponseDto> fakeStoreEmptyBodyException(FakeStoreException e){
        return buildResponse("Something went wrong", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ExceptionResponseDto> handleNotFound(Exception ex) {
        return buildResponse("Product not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ExceptionResponseDto> handleClientError(HttpClientErrorException ex) {
        return buildResponse("Client error: " + ex.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ExceptionResponseDto> handleServerError(HttpServerErrorException ex) {
        return buildResponse("FakeStore API is down", HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ExceptionResponseDto> handleTimeout(ResourceAccessException ex) {
        return buildResponse("Timeout or network issue", HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> productNotFoundException(ProductNotFoundException ex){
        return buildResponse(ex.getMessage() + ",Please check again", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> categoryNotFoundException(CategoryNotFoundException ex) {
        return buildResponse( ex.getMessage() + ",Please select the correct category", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryAlreadyPresentException.class)
    public ResponseEntity<ExceptionResponseDto> categoryAlreadyFoundException(CategoryAlreadyPresentException ex) {
        return buildResponse( ex.getMessage() + " Please try another category name", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ExceptionResponseDto> buildResponse(String message, HttpStatus status) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setMessage(message);
        response.setStatus(ExceptionStatus.FAILED);
        return new ResponseEntity<>(response, status);
    }
}
