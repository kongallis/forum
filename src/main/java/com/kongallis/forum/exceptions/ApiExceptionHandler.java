package com.kongallis.forum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, PostNotFoundException.class, CommentNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Europe/Athens")));
        return new ResponseEntity<>(apiException, notFound);
    }


}
