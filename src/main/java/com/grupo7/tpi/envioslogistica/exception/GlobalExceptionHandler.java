package com.grupo7.tpi.envioslogistica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrdenNoPagadaException.class)
    public ResponseEntity<String> handleOrdenNoPagada(OrdenNoPagadaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateOrdenException.class)
    public ResponseEntity<String> handleDuplicateOrden(DuplicateOrdenException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(EnvioNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EnvioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}