package com.grupo7.tpi.envioslogistica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un envío en el sistema de logística.
 */
@Entity
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ordenId;
    private String direccion;
    private String modalidad;
    private String estado;
    private LocalDateTime fechaCreacion;

    /**
     * Lista que almacena el historial completo de tracking del envío.
     */
    @OneToMany(mappedBy = "envio", cascade = CascadeType.ALL)
    private List<Tracking> historial = new ArrayList<>();

    /**
     * Referencia al estado de tracking actual del envío.
     */
    @OneToOne
    @JoinColumn(name = "tracking_id")
    private Tracking trackingActual;

    /**
     * Constructor por defecto.
     */
    public Envio() {}

    /**
     * Obtiene el ID del envío.
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del envío.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el ID de la orden asociada al envío.
     * @return
     */
    public String getOrdenId() {
        return ordenId;
    }

    /**
     * Establece el ID de la orden asociada al envío.
     * @param ordenId
     */
    public void setOrdenId(String ordenId) {
        this.ordenId = ordenId;
    }

    /**
     * Obtiene la dirección de entrega del envío.
     * @return
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección de entrega del envío.
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la modalidad del envío.
     * @return
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * Establece la modalidad del envío.
     * @param modalidad
     */
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
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
     * Obtiene la fecha de creación del envío.
     * @return
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del envío.
     * @param fechaCreacion
     */
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene el historial completo de tracking del envío.
     * @return
     */
    public List<Tracking> getHistorial() {
        return historial;
    }

    /**
     * Establece el historial completo de tracking del envío.
     * @param historial
     */
    public void setHistorial(List<Tracking> historial) {
        this.historial = historial;
    }

    /**
     * Obtiene el estado de tracking actual del envío.
     * @return
     */
    public Tracking getTrackingActual() {
        return trackingActual;
    }

    /**
     * Establece el estado de tracking actual del envío.
     * @param trackingActual
     */
    public void setTrackingActual(Tracking trackingActual) {
        this.trackingActual = trackingActual;
    }    
}