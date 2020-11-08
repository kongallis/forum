package com.kongallis.forum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = PostNotFoundException.class)
    public ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = CommentNotFoundException.class)
    public ResponseEntity<Object> handleCommentNotFoundException(CommentNotFoundException e){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, notFound);
    }
}
