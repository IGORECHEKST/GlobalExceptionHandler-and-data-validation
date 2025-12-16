package com.example.demo.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = processValidationErrors(ex);

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", "Bad Request");
        body.put("message", "Validation failed for request body.");
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", "Resource Not Found");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", "Bad Request");

        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getRootBeanClass().getSimpleName() + " " +
                        violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", "Conflict");
        body.put("message", "Data integrity violation. This resource might already exist " +
                "or contains invalid references.");

        if (ex.getCause() != null && ex.getCause().getMessage() != null) {
            body.put("details", ex.getCause().getMessage());
        }

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    private List<Map<String, String>> processValidationErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    Map<String, String> errorDetail = new HashMap<>();
                    String fieldName = (error instanceof FieldError)
                            ? ((FieldError) error).getField()
                            : error.getObjectName();
                    errorDetail.put("field", fieldName);
                    errorDetail.put("message", error.getDefaultMessage());
                    return errorDetail;
                })
                .collect(Collectors.toList());
    }
}
