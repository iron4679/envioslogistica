package com.grupo7.tpi.envioslogistica.observer;

import com.grupo7.tpi.envioslogistica.model.Envio;
import com.grupo7.tpi.envioslogistica.model.Tracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class NotificacionesObserver implements Observer {

    @Autowired
    private RestTemplate restTemplate;

    // Inyecta la URL desde application.properties
    @Value("${notificaciones.base-url}")
    private String baseUrl;

    @Override
    public void update(Envio envio, Tracking tracking, String usuarioId, String emailDestino) {
        System.out.println("[Notificaciones] Envío " + envio.getOrdenId() +
                           " cambió a estado: " + envio.getEstado() +
                           " en " + tracking.getTimestamp());

        Map<String, Object> body = Map.of(
            "canal", "EMAIL",
            "userId", usuarioId,
            "to", emailDestino,
            "template", "envio_" + tracking.getEstado().toLowerCase(),
            "vars", Map.of("ordenId", envio.getOrdenId())
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-Key", "token123"); // reemplazar con token real

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        restTemplate.postForEntity(baseUrl, request, Void.class);
    }
}