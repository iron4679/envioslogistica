package com.grupo7.tpi.envioslogistica.dto;

import java.util.List;
import java.time.LocalDateTime;
import com.grupo7.tpi.envioslogistica.model.Envio;

public class TrackingResponse {
    private String id; // ej: s345
    private String estado; // estado actual
    private List<TrackingItem> historial;

    public TrackingResponse() {}

    public TrackingResponse(Envio envio) {
        this.id = "s" + String.format("%03d", envio.getId());
        this.estado = envio.getEstado();
        this.historial = envio.getHistorial().stream()
            .map(t -> new TrackingItem(t.getTimestamp(), t.getEstado()))
            .toList();
    }

    public static class TrackingItem {
        private String t; // timestamp
        private String e; // estado

        public TrackingItem(LocalDateTime timestamp, String estado) {
            this.t = timestamp.toString() + "Z"; // formato ISO con Z
            this.e = estado;
        }

        public String getT() { return t; }
        public String getE() { return e; }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<TrackingItem> getHistorial() {
        return historial;
    }

    public void setHistorial(List<TrackingItem> historial) {
        this.historial = historial;
    }

    
}
