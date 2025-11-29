package com.grupo7.tpi.envioslogistica.config;

import com.grupo7.tpi.envioslogistica.service.EnvioService;
import com.grupo7.tpi.envioslogistica.observer.OrdenesObserver;
import com.grupo7.tpi.envioslogistica.observer.NotificacionesObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ObserversRegistrar {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private OrdenesObserver ordenesObserver;

    @Autowired
    private NotificacionesObserver notificacionesObserver;

    @PostConstruct
    public void register() {
        envioService.addObserver(ordenesObserver);
        envioService.addObserver(notificacionesObserver);
    }
}