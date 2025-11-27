package com.grupo7.tpi.envioslogistica.dto;

public class EnvioResponse {
    private String id;
    private String ordenId;
    private String estado;
    private String trackingActual;

    // Constructor vacío (necesario para Jackson)
    public EnvioResponse() {}

    public EnvioResponse(Long id, String ordenId, String estado, Long trackingId) {
        this.id = "s" + String.format("%03d", id);           // ej: s001
        this.ordenId = ordenId;
        this.estado = estado;
        this.trackingActual = String.format("TRK-%04d", trackingId); // ej: TRK-0001
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(String ordenId) {
        this.ordenId = ordenId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTrackingActual() {
        return trackingActual;
    }

    public void setTrackingActual(String trackingActual) {
        this.trackingActual = trackingActual;
    }
}