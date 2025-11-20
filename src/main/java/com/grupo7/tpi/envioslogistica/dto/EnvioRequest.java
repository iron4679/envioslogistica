package com.grupo7.tpi.envioslogistica.dto;

public class EnvioRequest {
    private String ordenId;
    private String direccion;
    private String modalidad;
    
    public String getOrdenId() {
        return ordenId;
    }
    public void setOrdenId(String ordenId) {
        this.ordenId = ordenId;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getModalidad() {
        return modalidad;
    }
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
}
