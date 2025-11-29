package com.grupo7.tpi.envioslogistica.observer;

import com.grupo7.tpi.envioslogistica.model.Envio;
import com.grupo7.tpi.envioslogistica.model.Tracking;

/**
 * Observer concreto para notificaciones.
 */
public class NotificacionesObserver implements Observer {
    /**
     * Actualiza el módulo de notificaciones cuando hay un cambio en el estado del envío.
     */
    @Override
    public void update(Envio envio, Tracking tracking) {
        System.out.println("[Notificaciones] Envío " + envio.getOrdenId() +
                           " cambió a estado: " + envio.getEstado() +
                           " en " + tracking.getTimestamp());
        // Acá iría la lógica real de integración con el módulo Notificaciones
    }
}