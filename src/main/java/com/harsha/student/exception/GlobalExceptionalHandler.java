package com.harsha.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler({InstructorNotFoundException.class,StudentNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleInstructorNotFoundEx(WebRequest request, Exception e) {

        return new ErrorResponse(HttpStatus.NOT_FOUND.toString(), new Date(), e.getMessage(), request.getDescription(false));
    }

}
