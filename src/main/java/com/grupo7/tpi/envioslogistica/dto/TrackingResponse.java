package com.grupo7.tpi.envioslogistica.dto;

import java.util.List;
import java.time.LocalDateTime;

public class TrackingResponse {
    private String id;
    private String estado;
    private List<TrackingItem> historial;

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

    public static class TrackingItem {
        private LocalDateTime t;
        private String e;
        
        public LocalDateTime getT() {
            return t;
        }
        public void setT(LocalDateTime t) {
            this.t = t;
        }
        public String getE() {
            return e;
        }
        public void setE(String e) {
            this.e = e;
        }
    }
}
