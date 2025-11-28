package com.grupo7.tpi.envioslogistica.observer;

import com.grupo7.tpi.envioslogistica.model.Envio;
import com.grupo7.tpi.envioslogistica.model.Tracking;

public class OrdenesObserver implements Observer {
    @Override
    public void update(Envio envio, Tracking tracking) {
        System.out.println("[Órdenes] La orden " + envio.getOrdenId() +
                           " fue actualizada al estado: " + envio.getEstado());
        // Acá iría la lógica real de integración con el módulo Órdenes
    }
}