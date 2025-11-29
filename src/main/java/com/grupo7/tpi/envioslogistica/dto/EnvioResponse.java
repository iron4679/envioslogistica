package com.grupo7.tpi.envioslogistica.dto;

/**
 * DTO para la respuesta de un envío.
 */
public class EnvioResponse {
    private String id;
    private String ordenId;
    private String estado;
    private String trackingActual;

    /**
     * Constructor por defecto.
     */
    public EnvioResponse() {}

    /**
     * Constructor con parámetros.
     * @param id
     * @param ordenId
     * @param estado
     * @param trackingId
     */
    public EnvioResponse(Long id, String ordenId, String estado, Long trackingId) {
        this.id = "s" + String.format("%03d", id);           // ej: s001
        this.ordenId = ordenId;
        this.estado = estado;
        this.trackingActual = String.format("TRK-%04d", trackingId); // ej: TRK-0001
    }

    /**
     * Obtiene el ID del envío.
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID del envío.
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el ID de la orden asociada al envío.
     * @return
     */
    public String getOrdenId() {
        return ordenId;
    }

    /**
     * Establece el ID de la orden asociada al envío.
     * @param ordenId
     */
    public void setOrdenId(String ordenId) {
        this.ordenId = ordenId;
    }

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

    /**
     * Obtiene el tracking actual del envío.
     * @return
     */
    public String getTrackingActual() {
        return trackingActual;
    }

    /**
     * Establece el tracking actual del envío.
     * @param trackingActual
     */
    public void setTrackingActual(String trackingActual) {
        this.trackingActual = trackingActual;
    }
}