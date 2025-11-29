package com.grupo7.tpi.envioslogistica.exception;

/**
 * Exepción personalizada para indicar que ya existe una orden con el mismo identificador.
 */
public class DuplicateOrdenException extends RuntimeException {
    /**
     * Constructor de la excepción que recibe un mensaje personalizado.
     *
     * @param message El mensaje de la excepción.
     */
    public DuplicateOrdenException(String message) {
        super(message);
    }
}