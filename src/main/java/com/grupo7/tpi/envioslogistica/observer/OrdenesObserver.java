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
public class OrdenesObserver implements Observer {

    @Autowired
    private RestTemplate restTemplate;

    // Inyecta la URL desde application.properties
    @Value("${ordenes.base-url}")
    private String baseUrl;

    @Override
    public void update(Envio envio, Tracking tracking, String usuarioId, String emailDestino) {
        System.out.println("[Órdenes] La orden " + envio.getOrdenId() +
                           " fue actualizada al estado: " + envio.getEstado());

        // Construir URL completa
        String url = baseUrl + "/" + envio.getOrdenId() + "/estado";

        Map<String, String> body = Map.of("estado", tracking.getEstado());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-Key", "token123"); // reemplazar con el token real

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        restTemplate.exchange(url, HttpMethod.PATCH, request, Void.class);
    }
}