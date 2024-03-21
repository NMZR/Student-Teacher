package com.nadia.studentsmgm.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ApiError> exceptionhandler(ResourceAccessException e, HttpServletRequest request){
        ApiError apierror = new ApiError(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()

                );
        return new ResponseEntity<>(apierror,HttpStatus.NOT_FOUND);
    }


}
