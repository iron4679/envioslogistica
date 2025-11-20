package com.grupo7.tpi.envioslogistica.dto;

public class EnvioResponse {
    private String id;
    private String ordenId;
    private String estado;
    private String tracking;

    public EnvioResponse(String id, String ordenId, String estado, String tracking) {
        this.id = id;
        this.ordenId = ordenId;
        this.estado = estado;
        this.tracking = tracking;
    }

    public String getId() {
        return id;
    }

    public String getOrdenId() {
        return ordenId;
    }

    public String getEstado() {
        return estado;
    }

    public String getTracking() {
        return tracking;
    }
}