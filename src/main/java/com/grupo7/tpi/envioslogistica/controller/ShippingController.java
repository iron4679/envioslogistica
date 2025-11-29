package com.grupo7.tpi.envioslogistica.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import com.grupo7.tpi.envioslogistica.service.EnvioService;
import com.grupo7.tpi.envioslogistica.dto.CotizacionRequest;
import com.grupo7.tpi.envioslogistica.dto.CotizacionResponse;

/**
 * Controlador REST para calcular cotizaciones de envíos.
 */
@RestController
@RequestMapping("/shipping-calculator")
public class ShippingController {

    /**
     * Servicio de envíos inyectado para manejar la lógica de negocio.
     */
    @Autowired
    private EnvioService envioService;

    /**
     * Calcula una cotización de envío.
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<CotizacionResponse> cotizar(@RequestBody CotizacionRequest request) {
        CotizacionResponse response = envioService.cotizar(request);
        return ResponseEntity.ok(response);
    }
}
