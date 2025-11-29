package com.grupo7.tpi.envioslogistica.observer;

import com.grupo7.tpi.envioslogistica.model.Envio;
import com.grupo7.tpi.envioslogistica.model.Tracking;

public interface Observer {
    /**
     * Método que se ejecuta cuando cambia el estado de un envío.
     * @param envio    El envío cuyo estado cambió
     * @param tracking El registro de tracking asociado al cambio
     * @param usuarioId El ID del usuario asociado al envío
     * @param emailDestino El email de destino para notificaciones
     */
    void update(Envio envio, Tracking tracking, String usuarioId, String emailDestino);
}