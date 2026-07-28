package org.example.oauthservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Map<String, Object>> handleAuthException(
            AuthException exception
    ) {

        return new ResponseEntity<>(
                errorBody(exception.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException exception
    ) {

        String message =
                exception
                        .getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();

        return new ResponseEntity<>(
                errorBody(message),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(
            Exception exception
    ) {

        return new ResponseEntity<>(
                errorBody(exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private Map<String, Object> errorBody(String message) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("hasError", true);
        body.put("errorMessage", message);
        return body;
    }
}
