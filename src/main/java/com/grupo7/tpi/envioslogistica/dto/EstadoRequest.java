package com.grupo7.tpi.envioslogistica.dto;

/**
 * DTO para la solicitud de actualización del estado de un envío.
 */
public class EstadoRequest {
    private String estado;

    /**
     * Obtiene el estado del envío.
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del envío.
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}