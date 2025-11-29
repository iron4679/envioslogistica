package com.grupo7.tpi.envioslogistica.dto;

/**
 * DTO para la respuesta de cotización de envío.
 */
public class CotizacionResponse {
    private int costo;
    private int etaDias;
    
    /**
     * Constructor vacío.
     */
    public CotizacionResponse() {
    }

    /**
     * Constructor con parámetros.
     * @param costo
     * @param etaDias
     */
    public CotizacionResponse(int costo, int etaDias) {
        this.costo = costo;
        this.etaDias = etaDias;
    }

    //getters y setters
    /**
     * Obtiene el costo de la cotización.
     * @return
     */
    public int getCosto() {
        return costo;
    }
    /**
     * Establece el costo de la cotización.
     * @param costo
     */
    public void setCosto(int costo) {
        this.costo = costo;
    }
    /**
     * Obtiene el tiempo estimado de llegada en días.
     * @return
     */
    public int getEtaDias() {
        return etaDias;
    }
    /**
     * Establece el tiempo estimado de llegada en días.
     * @param etaDias
     */
    public void setEtaDias(int etaDias) {
        this.etaDias = etaDias;
    }
}