package com.dovdau.vismatask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    // Add an exception handler for StudentNotFoundException
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<MeetingErrorResponse> handleException(MeetingNotFoundException exc) {

        MeetingErrorResponse response = new MeetingErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exc.getMessage());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Add an exception handler to catch any exception
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<MeetingErrorResponse> handleException(Exception exc) {

        MeetingErrorResponse response = new MeetingErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
