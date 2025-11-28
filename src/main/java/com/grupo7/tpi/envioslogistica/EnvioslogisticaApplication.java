package com.grupo7.tpi.envioslogistica;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.grupo7.tpi.envioslogistica.service.EnvioService;
import com.grupo7.tpi.envioslogistica.observer.OrdenesObserver;
import com.grupo7.tpi.envioslogistica.observer.NotificacionesObserver;

@SpringBootApplication
public class EnvioslogisticaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnvioslogisticaApplication.class, args);
    }

    @Bean
    public CommandLineRunner initObservers(EnvioService envioService) {
        return args -> {
            envioService.addObserver(new OrdenesObserver());
            envioService.addObserver(new NotificacionesObserver());

            System.out.println("Observers registrados: Órdenes y Notificaciones");
        };
    }
}