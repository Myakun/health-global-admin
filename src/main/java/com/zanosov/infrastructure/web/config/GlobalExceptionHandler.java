package com.zanosov.infrastructure.web.config;

import com.zanosov.domain.language.LanguageCodeAlreadyExistsException;
import com.zanosov.domain.language.LanguageNameAlreadyExistsException;
import com.zanosov.infrastructure.web.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public record ErrorResponse(String code, String message) {}

    public record ValidationErrorResponse(String code, String message, List<FieldViolation> violations) {}

    public record FieldViolation(String field, String message) {}

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotReadable() {
        return new ErrorResponse("BAD_REQUEST", "Request body is missing or malformed");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return new ErrorResponse("BAD_REQUEST", "Invalid value for parameter '%s': %s".formatted(ex.getName(), ex.getValue()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        List<FieldViolation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> new FieldViolation(e.getField(), e.getDefaultMessage()))
                .toList();
        return new ValidationErrorResponse("VALIDATION", "Validation failed", violations);
    }

    @ExceptionHandler(LanguageCodeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ValidationErrorResponse handleLanguageCodeAlreadyExists(LanguageCodeAlreadyExistsException ex) {
        return new ValidationErrorResponse("CONFLICT", ex.getMessage(), List.of(new FieldViolation("code", ex.getMessage())));
    }

    @ExceptionHandler(LanguageNameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ValidationErrorResponse handleLanguageNameAlreadyExists(LanguageNameAlreadyExistsException ex) {
        return new ValidationErrorResponse("CONFLICT", ex.getMessage(), List.of(new FieldViolation("name", ex.getMessage())));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoResourceFound(NoResourceFoundException ex) {
        return new ErrorResponse("NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleObjectNotFound(ObjectNotFoundException ex) {
        return new ErrorResponse("NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnexpected(Exception ex) {
        log.error("Unexpected error occurred.", ex);
        return new ErrorResponse("UNEXPECTED", "An unexpected error occurred");
    }
}
