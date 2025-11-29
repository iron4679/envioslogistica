package com.grupo7.tpi.envioslogistica.exception;

/**
 * Excepción personalizada para indicar que no se encontró un envío con el identificador proporcionado.
 */
public class EnvioNotFoundException extends RuntimeException {
    /**
     * Constructor de la excepción que recibe un mensaje personalizado.
     * @param message
     */
    public EnvioNotFoundException(String message) {
        super(message);
    }
}