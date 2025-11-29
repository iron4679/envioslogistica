package com.grupo7.tpi.envioslogistica.dto;

/**
 * DTO para la solicitud de creación de un envío.
 */
public class EnvioRequest {
    private String ordenId;
    private String direccion;
    private String modalidad;
    
    /**
     * Obtiene el ID de la orden.
     * @return
     */
    public String getOrdenId() {
        return ordenId;
    }
    /**
     * Establece el ID de la orden.
     * @param ordenId
     */
    public void setOrdenId(String ordenId) {
        this.ordenId = ordenId;
    }
    /**
     * Obtiene la dirección de envío.
     * @return
     */
    public String getDireccion() {
        return direccion;
    }
    /**
     * Establece la dirección de envío.
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
