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
import com.grupo7.tpi.envioslogistica.exception.EnvioNotFoundException;
import com.grupo7.tpi.envioslogistica.model.Envio;
import com.grupo7.tpi.envioslogistica.dto.EnvioResponse;
import com.grupo7.tpi.envioslogistica.model.Tracking;
import com.grupo7.tpi.envioslogistica.observer.Observer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

/*
    * Servicio para gestionar envíos y tracking
*/
@Service
public class EnvioService {
    @Autowired
    private EnvioRepository envioRepository; // Repositorio para envíos

    @Autowired
    private TrackingRepository trackingRepository; // Repositorio para tracking

    private List<Observer> observers = new ArrayList<>();

    /**
     * @brief Registra un nuevo observador en el servicio de envíos.
     *
     * Este método permite que otros módulos (por ejemplo, Órdenes o Notificaciones)
     * se suscriban a los cambios de estado de los envíos. Cada observador agregado
     * será notificado automáticamente cuando se produzca un cambio de estado.
     *
     * @param o El observador que se desea registrar.
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * @brief Notifica a todos los observadores registrados sobre un cambio de estado.
     *
     * Este método se invoca internamente cada vez que un envío cambia de estado.
     * Se pasa tanto el objeto @c Envio como el @c Tracking asociado al cambio,
     * de modo que los observadores puedan reaccionar con toda la información disponible.
     *
     * @param envio    El envío cuyo estado ha cambiado.
     * @param tracking El registro de tracking creado para reflejar el cambio de estado.
     */
    private void notifyObservers(Envio envio, Tracking tracking, String usuarioId, String emailDestino) {
        for (Observer o : observers) {
            o.update(envio, tracking, usuarioId, emailDestino);
        }
    }

    /**
     * Método para cotizar un envío
     * @param request
     * @return
    */
    public CotizacionResponse cotizar(CotizacionRequest request) {
        int costo = calcularCosto(request);
        int eta = calcularETA(request);
        return new CotizacionResponse(costo, eta);
    }

    /**
        * Constructor de EnvioService
    */
    public EnvioService(EnvioRepository envioRepository, TrackingRepository trackingRepository) {
        this.envioRepository = envioRepository; // Asignar el repositorio de envíos
        this.trackingRepository = trackingRepository; // Asignar el repositorio de tracking
    }

    /**
        * Método para crear un nuevo envío
        * @param request
    */
    public EnvioResponse crearEnvio(EnvioRequest request) {
    /*
        // Validar si la orden está pagada
        if (!ordenService.estaPagada(request.getOrdenId())) {
            throw new OrdenNoPagadaException("La orden " + request.getOrdenId() + " no está pagada");
        }
    */
        
        // Si ya existe, devolver el existente en vez de lanzar excepción
        Optional<Envio> existente = envioRepository.findByOrdenId(request.getOrdenId());
        if (existente.isPresent()) {
            Envio envio = existente.get();
            Tracking tracking = envio.getTrackingActual();
            return new EnvioResponse(envio.getId(), envio.getOrdenId(), envio.getEstado(), tracking.getId());
        }

        Envio envio = new Envio();
        envio.setOrdenId(request.getOrdenId());
        envio.setDireccion(request.getDireccion());
        envio.setModalidad(request.getModalidad());
        envio.setEstado("EN_PREPARACION");
        envio.setFechaCreacion(LocalDateTime.now());

        envio.setUsuarioId(request.getUsuarioId());
        envio.setEmailDestino(request.getEmailDestino());


        envioRepository.save(envio);

        // Crear primer tracking
        Tracking tracking = new Tracking();
        tracking.setEstado("EN_PREPARACION");
        tracking.setTimestamp(LocalDateTime.now());
        tracking.setEnvio(envio);
        trackingRepository.save(tracking);

        // Actualizar referencia al tracking actual
        envio.setTrackingActual(tracking);
        envio.getHistorial().add(tracking);
        envioRepository.save(envio);

        // Notificar observadores
        notifyObservers(envio, tracking, request.getUsuarioId(), request.getEmailDestino());

        // Devolver DTO con IDs formateados
        return new EnvioResponse(envio.getId(), envio.getOrdenId(), envio.getEstado(), tracking.getId());
    }

    /**
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

    /**
        * Método para actualizar el estado de un envío
        * @param envioId
        * @param nuevoEstado
    */
    public TrackingResponse actualizarEstado(Long envioId, String nuevoEstado) {
        Envio envio = envioRepository.findById(envioId)
            .orElseThrow(() -> new EnvioNotFoundException("Envio con id " + envioId + " no encontrado"));

        // Actualizar estado del envío
        envio.setEstado(nuevoEstado);

        // Crear nuevo tracking
        Tracking tracking = new Tracking();
        tracking.setEstado(nuevoEstado);
        tracking.setTimestamp(LocalDateTime.now());
        tracking.setEnvio(envio);

        trackingRepository.save(tracking);

        // Actualizar referencia al tracking actual y el historial
        envio.setTrackingActual(tracking);
        envio.getHistorial().add(tracking);

        envioRepository.save(envio);

        // Notificar observadores
        notifyObservers(envio, tracking, envio.getUsuarioId(), envio.getEmailDestino());

        // 👇 devolver DTO con historial
        return new TrackingResponse(envio);
    }

    /**
        * Método para calcular el costo del envío
        * @param r
        * @return
    */
    private int calcularCosto(CotizacionRequest r) {
        return (int)(r.getPeso() * 1000);
    }

    /**
        * Método para calcular el ETA del envío
        * @param r
        * @return
    */
    private int calcularETA(CotizacionRequest r) {
        return "EXPRESS".equals(r.getModalidad()) ? 2 : 5;
    }
}