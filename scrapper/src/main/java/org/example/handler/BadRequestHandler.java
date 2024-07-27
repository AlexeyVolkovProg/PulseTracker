package org.example.handler;


import org.example.exception.ChatNotFoundException;
import org.example.exception.LinkNotFoundException;
import org.example.scrapperrestapi.dto.error.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Отлавливает и обрабатывает исключения ScrapperApiController
 */
@ControllerAdvice
public class BadRequestHandler {

    public ResponseEntity<ApiErrorResponse> handleValidationException(Exception ex, String description) {
        ApiErrorResponse response = new ApiErrorResponse(
                description,
                HttpStatus.BAD_REQUEST.toString(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex){
        return handleValidationException(ex, "Исключение не соответствия типа");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex){
        return handleValidationException(ex, "Исключение отсутствия ресурса");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        return handleValidationException(ex, "Исключение, невозможно прочитать сообщение");
    }

    @ExceptionHandler(ChatNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleChatNotFoundException(ChatNotFoundException ex){
        return handleValidationException(ex, ex.getMessage());
    }

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleLinkNotFoundException(LinkNotFoundException ex){
        return handleValidationException(ex, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleOtherExceptions(Exception ex){
        return handleValidationException(ex, "Непредвиденное исключение");
    }


}
