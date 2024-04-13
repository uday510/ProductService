package com.app.productservice.exceptionhandler;

import com.app.productservice.dtos.ExceptionDto;
import com.app.productservice.dtos.ProductNotFoundExceptionDto;
import exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(Exception e) {
        // Handle exception
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("An error occurred");
        exceptionDto.setResolution("Please try again later");

        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ExceptionDto> handleArrayIndexOutOfBoundsException(Exception e) {
        // Handle exception
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException e) {
        // Handle exception
        ProductNotFoundExceptionDto productNotFoundExceptionDto = new ProductNotFoundExceptionDto();

        productNotFoundExceptionDto.setMessage(e.getMessage());

        return new ResponseEntity<>(productNotFoundExceptionDto, HttpStatus.NOT_FOUND);
    }
}
