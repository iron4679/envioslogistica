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

    // Relación uno a muchos: historial completo
    @OneToMany(mappedBy = "envio", cascade = CascadeType.ALL)
    private List<Tracking> historial = new ArrayList<>();

    // Relación uno a uno: tracking actual
    @OneToOne
    @JoinColumn(name = "tracking_id")
    private Tracking trackingActual;

    // Constructor vacío requerido por JPA
    public Envio() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Tracking> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Tracking> historial) {
        this.historial = historial;
    }

    public Tracking getTrackingActual() {
        return trackingActual;
    }

    public void setTrackingActual(Tracking trackingActual) {
        this.trackingActual = trackingActual;
    }    
}