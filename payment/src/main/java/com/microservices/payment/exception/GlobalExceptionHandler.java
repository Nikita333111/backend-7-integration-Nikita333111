package com.microservices.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Обработчик для OrderNotFoundException
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        // Создание сообщения об ошибке
        String errorMessage = ex.getMessage();
        // Возврат ответа с сообщением об ошибке и статусом NOT_FOUND
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
