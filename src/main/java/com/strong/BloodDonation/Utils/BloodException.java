package com.strong.BloodDonation.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BloodException extends Exception {

    public BloodException() {
        super();
    }

    public BloodException(String Message) {
        super(Message);
    }

    public BloodException(String Message, Throwable throwable) {
        super(Message, throwable);
    }

    public BloodException(Throwable throwable) {
        super(throwable);
    }

    @ExceptionHandler
    public ResponseEntity<BloodResponse> ErrorException(Exception exception) {
        BloodResponse response = new BloodResponse();
        response.setMessage(exception.getLocalizedMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}