package com.grupo7.tpi.envioslogistica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.time.LocalDateTime;

/**
 * Clase que representa el seguimiento de un envío.
 */
@Entity
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "envio_id")
    private Envio envio;

    /**
     * Constructor por defecto.
     */
    public Tracking() {}

    /**
     * Obtiene el ID del seguimiento.
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del seguimiento.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el estado del envío en este punto de seguimiento.
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del envío en este punto de seguimiento.
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la marca de tiempo del seguimiento.
     * @return
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la marca de tiempo del seguimiento.
     * @param timestamp
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Obtiene el envío asociado a este seguimiento.
     * @return
     */
    public Envio getEnvio() {
        return envio;
    }

    /**
     * Establece el envío asociado a este seguimiento.
     * @param envio
     */
    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
}