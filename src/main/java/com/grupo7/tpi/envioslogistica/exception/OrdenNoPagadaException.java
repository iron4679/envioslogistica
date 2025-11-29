package com.grupo7.tpi.envioslogistica.exception;

/**
 * Excepción personalizada para indicar que una orden no ha sido pagada.
 */
public class OrdenNoPagadaException extends RuntimeException {
    /**
     * Constructor de la excepción que recibe un mensaje personalizado.
     * @param message
     */
    public OrdenNoPagadaException(String message) {
        super(message);
    }
}