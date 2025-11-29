package com.grupo7.tpi.envioslogistica.dto;

/**
 * DTO para la solicitud de cotización de envío.
 */
public class CotizacionRequest {
    private String cp;
    private double peso;
    private double volumen;
    private String modalidad;
    
    //getters y setters
    /**
     * Obtiene el código postal.
     * @return
     */
    public String getCp() {
        return cp;
    }
    /**
     * Establece el código postal.
     * @param cp
     */
    public void setCp(String cp) {
        this.cp = cp;
    }
    /**
     * Obtiene el peso del envío.
     * @return
     */
    public double getPeso() {
        return peso;
    }
    /**
     * Establece el peso del envío.
     * @param peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }
    /**
     * Obtiene el volumen del envío.
     * @return
     */
    public double getVolumen() {
        return volumen;
    }
    /**
     * Establece el volumen del envío.
     * @param volumen
     */
    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }
    /**
     * Obtiene la modalidad de envío.
     * @return
     */
    public String getModalidad() {
        return modalidad;
    }
    /**
     * Establece la modalidad de envío.
     * @param modalidad
     */
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
    
}