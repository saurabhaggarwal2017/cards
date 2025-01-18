package com.eazybytes.cards.exception;

import com.eazybytes.cards.dto.ErrorResponseDto;
import com.eazybytes.cards.dto.ValidationErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleBiFunction;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFoundExceptionHandler(ResourceNotFoundException exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                apiPath,
                HttpStatus.NOT_FOUND.toString(),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        Map<String, String> allErrorsMap = new HashMap<>();
        for (ObjectError error : allErrors) {
            allErrorsMap.put(((FieldError) error).getField(), error.getDefaultMessage());
        }
        ValidationErrorResponseDto errorResponseDto = new ValidationErrorResponseDto(
                apiPath,
                allErrorsMap.size(),
                HttpStatus.BAD_REQUEST.toString(),
                "There are some input validation errors.",
                allErrorsMap,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> constraintViolationExceptionHandler(ConstraintViolationException exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        String[] splitByComa = exception.getMessage().split(",");
        List<String> messageList = new ArrayList<>();
        for (String str : splitByComa) {
            String[] splitByColon = str.split(":");
            messageList.add(splitByColon[1].trim());
        }
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                apiPath,
                HttpStatus.BAD_REQUEST.toString(),
                messageList.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> exceptionHandler(Exception exception, HttpServletRequest request) {
        String apiPath = String.format("%s %s", request.getMethod(), request.getRequestURI());
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                apiPath,
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }
}
