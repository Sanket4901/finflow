package com.finflow.exception;


import com.finflow.dto.response.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(
            EmailAlreadyExistsException ex){

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());

    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> invalidCredetials(InvalidCredentialsException inv){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(inv.getMessage());
        }


}
