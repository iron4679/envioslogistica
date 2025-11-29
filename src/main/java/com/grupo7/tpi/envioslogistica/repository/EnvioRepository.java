package com.grupo7.tpi.envioslogistica.repository;


import com.grupo7.tpi.envioslogistica.model.Envio;
import com.grupo7.tpi.envioslogistica.model.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad {@link Envio}.
 * 
 * Extiende {@link JpaRepository}, proporcionando operaciones CRUD básicas
 * y permitiendo definir consultas personalizadas mediante convenciones
 * de nombres de Spring Data JPA.
 * 
 * Este repositorio se utiliza para gestionar envíos, incluyendo búsquedas
 * por estado, orden y tracking asociado.
 */
@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
    /**
     * Buscar envíos por estado.
     * @param estado
     * @return
     */
    List<Envio> findByEstado(String estado);

    /**
     * Buscar por ordenId
     * @param ordenId
     * @return
     */
    Optional<Envio> findByOrdenId(String ordenId);

    /**
     * Buscar por tracking actual
     * @param tracking
     * @return
     */
    Optional<Envio> findByTrackingActual(Tracking tracking);

     /**
     * Verifica si existe un envío asociado a un identificador de orden específico.
     * @param ordenId el identificador único de la orden
     * @return {@code true} si existe un envío con ese ordenId, {@code false} en caso contrario
     */
    boolean existsByOrdenId(String ordenId);
}