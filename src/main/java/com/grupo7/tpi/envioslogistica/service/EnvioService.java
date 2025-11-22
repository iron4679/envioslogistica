/*
    * Servicio para gestionar envíos y tracking
*/
package com.grupo7.tpi.envioslogistica.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.grupo7.tpi.envioslogistica.repository.EnvioRepository;
import com.grupo7.tpi.envioslogistica.repository.TrackingRepository;
import com.grupo7.tpi.envioslogistica.dto.CotizacionRequest;
import com.grupo7.tpi.envioslogistica.dto.CotizacionResponse;
import com.grupo7.tpi.envioslogistica.dto.EnvioRequest;
import com.grupo7.tpi.envioslogistica.dto.TrackingResponse;
import com.grupo7.tpi.envioslogistica.model.Envio;
import com.grupo7.tpi.envioslogistica.dto.EnvioResponse;
import com.grupo7.tpi.envioslogistica.model.Tracking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*
    * Servicio para gestionar envíos y tracking
*/
@Service
public class EnvioService {
    @Autowired
    private EnvioRepository envioRepository; // Repositorio para envíos

    @Autowired
    private TrackingRepository trackingRepository; // Repositorio para tracking

    /*
     * Método para cotizar un envío
     * @param request
     * @return
    */
    public CotizacionResponse cotizar(CotizacionRequest request) {
        int costo = calcularCosto(request);
        int eta = calcularETA(request);
        return new CotizacionResponse(costo, eta);
    }

    /*
        * Constructor de EnvioService
    */
    public EnvioService(EnvioRepository envioRepository, TrackingRepository trackingRepository) {
        this.envioRepository = envioRepository; // Asignar el repositorio de envíos
        this.trackingRepository = trackingRepository; // Asignar el repositorio de tracking
    }

    /*
        * Método para crear un nuevo envío
        * @param request
    */
    public EnvioResponse crearEnvio(EnvioRequest request) {
        Envio envio = new Envio();
        envio.setOrdenId(request.getOrdenId());
        envio.setDireccion(request.getDireccion());
        envio.setModalidad(request.getModalidad());
        envio.setEstado("EN_PREPARACION");
        envio.setFechaCreacion(LocalDateTime.now());

        envioRepository.save(envio);

        // Crear primer tracking
        Tracking tracking = new Tracking();
        tracking.setEstado("EN_PREPARACION");
        tracking.setTimestamp(LocalDateTime.now());
        tracking.setEnvio(envio);
        trackingRepository.save(tracking);

        // Actualizar referencia al tracking actual
        envio.setTrackingActual(tracking);
        envioRepository.save(envio);

        // Devolver DTO con IDs formateados
        return new EnvioResponse(envio.getId(), envio.getOrdenId(), envio.getEstado(), tracking.getId());
    }

    /*
        * Método para obtener el tracking de un envío
        * @param envioId
        * @return
    */
    public TrackingResponse obtenerTracking(Long envioId) {
        Envio envio = envioRepository.findById(envioId)
            .orElseThrow(() -> new RuntimeException("Envio no encontrado"));

        List<Tracking> historial = trackingRepository.findByEnvioOrderByTimestampAsc(envio);

        List<TrackingResponse.TrackingItem> items = historial.stream()
            .map(t -> new TrackingResponse.TrackingItem(t.getTimestamp(), t.getEstado()))
            .collect(Collectors.toList());
        
        TrackingResponse response = new TrackingResponse();
        response.setId("s" + String.format("%03d", envio.getId()));
        response.setEstado(envio.getEstado());
        response.setHistorial(items);
        return response;
    }

    /*
        * Método para actualizar el estado de un envío
        * @param envioId
        * @param nuevoEstado
    */
    public void actualizarEstado(Long envioId, String nuevoEstado) {
        Envio envio = envioRepository.findById(envioId)
            .orElseThrow(() -> new RuntimeException("Envio no encontrado"));

        Tracking tracking = new Tracking();
        tracking.setEstado(nuevoEstado);
        tracking.setTimestamp(LocalDateTime.now());
        tracking.setEnvio(envio);
        trackingRepository.save(tracking); //  ID generado por DB
    }

    /*
        * Método para calcular el costo del envío
        * @param r
        * @return
    */
    private int calcularCosto(CotizacionRequest r) {
        return (int)(r.getPeso() * 1000);
    }

    /*
        * Método para calcular el ETA del envío
        * @param r
        * @return
    */
    private int calcularETA(CotizacionRequest r) {
        return "EXPRESS".equals(r.getModalidad()) ? 2 : 5;
    }
}