package com.grupo7.tpi.envioslogistica.dto;

import java.util.List;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.grupo7.tpi.envioslogistica.model.Envio;

/**
 * DTO para la respuesta de tracking de un envío.
 */
@JsonPropertyOrder({ "id", "estado", "historial" })
public class TrackingResponse {
    private String id; // ej: s345
    private String estado; // estado actual
    private List<TrackingItem> historial;

    /**
     * Constructor por defecto.
     */
    public TrackingResponse() {}

    /**
     * Constructor que mapea un objeto Envio al DTO.
     * @param envio
     */
    public TrackingResponse(Envio envio) {
        this.id = "s" + String.format("%03d", envio.getId());
        this.estado = envio.getEstado();
        this.historial = envio.getHistorial().stream()
            .map(t -> new TrackingItem(t.getTimestamp(), t.getEstado()))
            .toList();
    }

    /**
     * Clase interna para representar un ítem del historial de tracking.
     */
    @JsonPropertyOrder({ "t", "e" })
    public static class TrackingItem {
        private String t; // timestamp
        private String e; // estado

        /**
         * Constructor con parámetros.
         * @param timestamp
         * @param estado
         */
        public TrackingItem(LocalDateTime timestamp, String estado) {
            this.t = timestamp.toString() + "Z"; // formato ISO con Z
            this.e = estado;
        }

        /**
         * Obtiene el timestamp.
         * @return
         */
        public String getT() { return t; }
        public String getE() { return e; }
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
     * Obtiene el estado actual del envío.
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual del envío.
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el historial de tracking.
     * @return
     */
    public List<TrackingItem> getHistorial() {
        return historial;
    }

    /**
     * Establece el historial de tracking.
     * @param historial
     */
    public void setHistorial(List<TrackingItem> historial) {
        this.historial = historial;
    }
}
