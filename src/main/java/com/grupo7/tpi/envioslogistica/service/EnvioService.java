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
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvioService {
    private static int contadorEnvios = 1;
    private static int contadorTracking = 1;

    private String generarId() {
        return String.format("s%03d", contadorEnvios++);
    }

    private String generarTracking() {
        return String.format("TRK-%04d", contadorTracking++);
    }

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private TrackingRepository trackingRepository;

    public CotizacionResponse cotizar(CotizacionRequest request) {
        int costo = calcularCosto(request);
        int eta = calcularETA(request);
        return new CotizacionResponse(costo, eta);
    }

    public EnvioResponse crearEnvio(EnvioRequest request) {
        Envio envio = new Envio();
        envio.setId(generarId());
        envio.setOrdenId(request.getOrdenId());
        envio.setDireccion(request.getDireccion());
        envio.setModalidad(request.getModalidad());
        envio.setEstado("EN_PREPARACION");
        envio.setTracking(generarTracking());
        envio.setFechaCreacion(LocalDateTime.now());
        envioRepository.save(envio);

        Tracking tracking = new Tracking();
        tracking.setId(UUID.randomUUID().toString());
        tracking.setEnvio(envio);
        tracking.setEstado("EN_PREPARACION");
        tracking.setTimestamp(LocalDateTime.now());
        trackingRepository.save(tracking);

        return new EnvioResponse(
            envio.getId(),
            envio.getOrdenId(),
            envio.getEstado(),
            envio.getTracking()
        );
    }

    public TrackingResponse obtenerTracking(String envioId) {
        Envio envio = envioRepository.findById(envioId)
            .orElseThrow(() -> new RuntimeException("Envio no encontrado"));

        List<Tracking> historial = trackingRepository.findByEnvioIdOrderByTimestampAsc(envioId);
        List<TrackingResponse.TrackingItem> items = historial.stream().map(t -> {
            TrackingResponse.TrackingItem item = new TrackingResponse.TrackingItem();
            item.setT(t.getTimestamp());
            item.setE(t.getEstado());
            return item;
        }).collect(Collectors.toList());

        TrackingResponse response = new TrackingResponse();
        response.setId(envio.getId());
        response.setEstado(envio.getEstado());
        response.setHistorial(items);
        return response;
    }

    public void actualizarEstado(String envioId, String nuevoEstado) {
        Envio envio = envioRepository.findById(envioId)
            .orElseThrow(() -> new RuntimeException("Envio no encontrado"));

        // Validar transición si querés
        envio.setEstado(nuevoEstado);
        envioRepository.save(envio);

        Tracking tracking = new Tracking();
        tracking.setId(UUID.randomUUID().toString());
        tracking.setEnvio(envio);
        tracking.setEstado(nuevoEstado);
        tracking.setTimestamp(LocalDateTime.now());
        trackingRepository.save(tracking);
    }

    private int calcularCosto(CotizacionRequest r) {
        return (int)(r.getPeso() * 1000);
    }

    private int calcularETA(CotizacionRequest r) {
        return "EXPRESS".equals(r.getModalidad()) ? 2 : 5;
    }
}