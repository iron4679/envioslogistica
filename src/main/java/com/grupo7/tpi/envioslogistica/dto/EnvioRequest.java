package com.grupo7.tpi.envioslogistica.dto;

public class EnvioRequest {
    private String ordenId;
    private String direccion;
    private String modalidad;

    // Campos adicionales solo para notificaciones
    private String usuarioId;
    private String emailDestino;
    
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
    public String getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getEmailDestino() {
        return emailDestino;
    }
    public void setEmailDestino(String emailDestino) {
        this.emailDestino = emailDestino;
    }
}
