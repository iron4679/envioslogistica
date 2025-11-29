package com.grupo7.tpi.envioslogistica.observer;

import com.grupo7.tpi.envioslogistica.model.Envio;
import com.grupo7.tpi.envioslogistica.model.Tracking;

/**
 * Observer concreto para el módulo de Órdenes.
 */
public class OrdenesObserver implements Observer {
    /**
     * Actualiza el módulo de Órdenes cuando hay un cambio en el estado del envío.
     */
    @Override
    public void update(Envio envio, Tracking tracking) {
        /**
         * Aquí se simula la actualización del módulo de Órdenes.
         */
        System.out.println("[Órdenes] La orden " + envio.getOrdenId() +
                           " fue actualizada al estado: " + envio.getEstado());
        // Acá iría la lógica real de integración con el módulo Órdenes
    }
}