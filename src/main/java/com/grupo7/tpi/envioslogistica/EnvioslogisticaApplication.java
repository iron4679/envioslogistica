package com.grupo7.tpi.envioslogistica;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.grupo7.tpi.envioslogistica.service.EnvioService;
import com.grupo7.tpi.envioslogistica.observer.OrdenesObserver;
import com.grupo7.tpi.envioslogistica.observer.NotificacionesObserver;

/**
 * Clase principal de la aplicación de logística y envíos.
 * Esta clase arranca el contexto de Spring Boot y configura los
 * componentes iniciales necesarios para el funcionamiento del sistema.
 *
 * Además, registra los observers {@link OrdenesObserver} y
 * {@link NotificacionesObserver} en el {@link EnvioService}, siguiendo
 * el patrón de diseño <b>Observer</b> para reaccionar a cambios en los envíos.
 */
@SpringBootApplication
public class EnvioslogisticaApplication {
    
    /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(EnvioslogisticaApplication.class, args);
    }

    /**
     * Inicializa y registra los observers en el servicio de envíos.
     * <p>
     * Este método se ejecuta al arrancar la aplicación y asegura que
     * los observers de órdenes y notificaciones estén activos para
     * recibir actualizaciones de estado.
     * </p>
     *
     * @param envioService el servicio de envíos donde se registran los observers
     * @return un {@link CommandLineRunner} que agrega los observers al servicio
     */
    @Bean
    public CommandLineRunner initObservers(EnvioService envioService) {
        return args -> {
            envioService.addObserver(new OrdenesObserver());
            envioService.addObserver(new NotificacionesObserver());

            System.out.println("Observers registrados: Órdenes y Notificaciones");
        };
    }
}