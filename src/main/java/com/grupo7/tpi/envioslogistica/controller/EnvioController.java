/*
    * Controlador REST para gestionar envíos y su tracking.
    * Proporciona endpoints para crear envíos, ver el estado de tracking y actualizar el estado del envío.
*/
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

/**
 * Controlador REST para gestionar envíos y su tracking.
 */

@RestController
@RequestMapping("/shipments")
public class EnvioController {

    /**
     * Servicio de envíos inyectado para manejar la lógica de negocio.
     */
    @Autowired
    private EnvioService envioService;

    //  HEALTH CHECK
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }
    
    /**
     * Crea un nuevo envío.
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<EnvioResponse> crearEnvio(@RequestBody EnvioRequest request) {
        EnvioResponse response = envioService.crearEnvio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 con body
    }


    /**
     * Obtiene el estado de tracking de un envío por su ID.
     * @param id
     * @return
     */
    @GetMapping("/{id}/tracking")
    public ResponseEntity<TrackingResponse> verTracking(@PathVariable Long id) {
        TrackingResponse tracking = envioService.obtenerTracking(id);
        if (tracking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }
        return ResponseEntity.ok(tracking); // 200
    }

    /**
     * Actualiza el estado de un envío.
     * @param id
     * @param estado
     * @return
     */
    @PatchMapping("/{id}/tracking")
    public ResponseEntity<TrackingResponse> actualizarEstado(@PathVariable Long id, @RequestBody EstadoRequest estado) {
        TrackingResponse tracking = envioService.actualizarEstado(id, estado.getEstado());
        return ResponseEntity.ok(tracking); // 200 OK
    }
}
