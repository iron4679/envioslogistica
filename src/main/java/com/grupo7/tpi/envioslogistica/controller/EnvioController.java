package com.grupo7.tpi.envioslogistica.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.grupo7.tpi.envioslogistica.service.EnvioService;
import com.grupo7.tpi.envioslogistica.dto.EnvioRequest;
import com.grupo7.tpi.envioslogistica.dto.EstadoRequest;
import com.grupo7.tpi.envioslogistica.dto.TrackingResponse;
import com.grupo7.tpi.envioslogistica.dto.EnvioResponse;

@RestController
@RequestMapping("/shipments")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @PostMapping
    public ResponseEntity<EnvioResponse> crearEnvio(@RequestBody EnvioRequest request) {
        EnvioResponse response = envioService.crearEnvio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}/tracking")
    public ResponseEntity<TrackingResponse> verTracking(@PathVariable String id) {
        return ResponseEntity.ok(envioService.obtenerTracking(id));
    }

    @PatchMapping("/{id}/tracking")
    public ResponseEntity<Void> actualizarEstado(@PathVariable String id, @RequestBody EstadoRequest estado) {
        envioService.actualizarEstado(id, estado.getEstado());
        return ResponseEntity.ok().build();
    }
}
