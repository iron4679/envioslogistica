package com.grupo7.tpi.envioslogistica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manejador global de excepciones para la aplicación.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción OrdenNoPagadaException.
     * @param ex
     * @return
     */
    @ExceptionHandler(OrdenNoPagadaException.class)
    /**
     * Maneja la excepción OrdenNoPagadaException.
     * @param ex
     * @return
     */
    public ResponseEntity<String> handleOrdenNoPagada(OrdenNoPagadaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    /**
     * Maneja la excepción DuplicateOrdenException.
     * @param ex
     * @return
     */
    @ExceptionHandler(DuplicateOrdenException.class)
    /**
     * Maneja la excepción DuplicateOrdenException.
     * @param ex
     * @return
     */
    public ResponseEntity<String> handleDuplicateOrden(DuplicateOrdenException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    /**
     * Maneja la excepción EnvioNotFoundException.
     * @param ex
     * @return
     */
    @ExceptionHandler(EnvioNotFoundException.class)
    /**
     * Maneja la excepción EnvioNotFoundException.
     * @param ex
     * @return
     */
    public ResponseEntity<String> handleNotFound(EnvioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}