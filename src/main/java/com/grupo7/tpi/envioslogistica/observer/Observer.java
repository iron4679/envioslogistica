package com.grupo7.tpi.envioslogistica.observer;

import com.grupo7.tpi.envioslogistica.model.Envio;
import com.grupo7.tpi.envioslogistica.model.Tracking;

public interface Observer {
    /**
     * Método que se ejecuta cuando cambia el estado de un envío.
     * @param envio    El envío cuyo estado cambió
     * @param tracking El registro de tracking asociado al cambio
     */
    void update(Envio envio, Tracking tracking);
}