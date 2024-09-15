package com.abdhage.rentail.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.security.SignatureException;
import java.util.*;

@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        final List<Map<String, String>> fieldErrors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            final Map<String, String> error = new HashMap<>();
            System.out.println(fieldError + " Field Error");
            error.put("message", fieldError.getDefaultMessage());
            error.put("code", fieldError.getCode());
            error.put("field", fieldError.getField());
            fieldErrors.add(error);
        });

        ProblemDetail problemDetail = null;

        problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, "Failed to validate the request");

        problemDetail.setProperty("errors", fieldErrors);

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception exception) {
        ProblemDetail errorDetail = null;

        // TODO send this stack trace to an observability tool
        log.error(Arrays.toString(exception.getStackTrace()) + "Stack Trace");

        if (exception instanceof MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
            String message = getMessage(methodArgumentTypeMismatchException);

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, message);
        } else if (exception instanceof ServletException) {

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            if (exception instanceof NoResourceFoundException) {
                errorDetail.setStatus(HttpStatus.NOT_FOUND);
            } else if (exception instanceof HttpRequestMethodNotSupportedException) {
                errorDetail.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
            }

        } else if (exception instanceof ResponseStatusException responseStatusException) {
            System.out.println(responseStatusException.getReason() + " Reason");
            errorDetail = ProblemDetail.forStatusAndDetail(responseStatusException.getStatusCode(), responseStatusException.getReason());
        } else if (exception instanceof MalformedJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.getMessage());
            errorDetail.setProperty("description", "Jwt Malformed");
        } else if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect");
        } else if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");
        } else if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");
        } else if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");
        } else if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");
        } else {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            errorDetail.setProperty("description", "Internal server error.");
        }

        errorDetail.setProperty("name", exception.getClass().getSimpleName());

        return errorDetail;
    }

    private static String getMessage(MethodArgumentTypeMismatchException exception) {
        String message = String.format("Invalid value '%s' for parameter '%s'. Expected type: %s.",
                exception.getValue(), exception.getName(), exception.getRequiredType().getSimpleName());

        if (exception.getRequiredType().getSimpleName().equals("UUID")) {
            message = "Invalid UUID format";
        }
        return message;
    }

}
