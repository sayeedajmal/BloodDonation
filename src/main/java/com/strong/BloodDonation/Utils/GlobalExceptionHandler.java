package com.strong.BloodDonation.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BloodException.class)
    public ResponseEntity<?> handleBloodException(BloodException exception) {
        BloodResponse response = new BloodResponse();
        response.setMessage(exception.getLocalizedMessage());
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}